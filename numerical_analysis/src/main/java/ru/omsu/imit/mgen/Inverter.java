package ru.omsu.imit.mgen;

public class Inverter {

    public void rotate(double[][] a, double[][] b, int n, int col, int i, int j) {
        double s;
        double c;
        s = -a[i][col]/Math.sqrt(a[i][col]*a[i][col]+a[j][col]*a[j][col]);
        c = a[j][col]/Math.sqrt(a[i][col]*a[i][col]+a[j][col]*a[j][col]);
        double aj, ai;
        for(int k = 0; k < n; k++) {
            aj = a[j][k];
            ai = a[i][k];

            a[j][k] = c * aj - s * ai;
            a[i][k] = s * aj + c * ai;
        }
        a[i][col] = 0.;
        double bj, bi;
        for(int k = 0; k < n; k++) {
            bj = b[j][k];
            bi = b[i][k];

            b[j][k] = c * bj - s * bi;
            b[i][k] = s * bj + c * bi;
        }
    }

    public void triangulate(double[][] a, double[][] b, int n) {
        for(int j = 0; j < n-1; j++) {
            for(int i = j+1; i < n; i++) {
                rotate(a, b, n, j, i, j);
            }
        }
    }

    public double[][] createIdentityMatrix(int n) {
        double[][] ret = new double[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    ret[i][j] = 1.;
                } else {
                    ret[i][j] = 0.;
                }
            }
        }
        return ret;
    }

    public void invertTriangle(double[][] a, int n) {
        double[] arr = new double[n];
        for(int i = n-1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                arr[j] = a[i][j];
            }
            a[i][i] = 1./arr[i];
            for(int j = i+1; j < n; j++) {
                a[i][j] = 0;
                for(int k = i+1; k <= j; k++) {
                    a[i][j] -= arr[k] * a[k][j];
                }
                a[i][j] /= arr[i];
            }
        }
        /*for(int i = n-1; i >=0; i--) {
            for(int j = 0; j < i; j++) {
                a[i][j] = 0;
            }
        }*/
    }

    public void invertMatrix(double[][] a, int n) {

        Gen gen = new Gen();

        double[][] b = createIdentityMatrix(n);

        triangulate(a, b, n);

        gen.print_matr(a, n);

        invertTriangle(a, n);

        gen.print_matr(a, n);

        double[][] c = new double[n][n];


        gen.matr_mul(a, b, c, n);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                a[i][j] = c[i][j];
            }
        }
    }


}
