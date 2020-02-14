package ru.omsu.imit.mgen;

public class Inverter {




    public void rotateWithIdentityMatrix(double[][] a, double[][] b, int n, int col, int i, int j) {
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

    public void triangulateWithIdentityMatrix(double[][] a, double[][] b, int n) {
        for(int j = 0; j < n-1; j++) {
            for(int i = j+1; i < n; i++) {
                rotateWithIdentityMatrix(a, b, n, j, i, j);
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
    }

    public void invertMatrix(double[][] a, int n) {

        Gen gen = new Gen();

        double[][] b = createIdentityMatrix(n);

        triangulateWithIdentityMatrix(a, b, n);

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


    /**
     * Выполняет одно зануление в алгоритме приведения к верхнетреугольному виду, не трогая нули, полученные ранее
     */
    public void rotate(double[][] a, int n, int col, int i, int j) {
        double c;
        double s;
        c = a[j][col]/Math.sqrt(a[i][col]*a[i][col]+a[j][col]*a[j][col]);
        s = -a[i][col]/Math.sqrt(a[i][col]*a[i][col]+a[j][col]*a[j][col]);
        double aj, ai;
        for(int k = col; k < n; k++) {
            aj = a[j][k];
            ai = a[i][k];

            a[j][k] = c * aj - s * ai;
            a[i][k] = s * aj + c * ai;
        }
        saveTransformationParams(a, i, col, c, s);
    }

    public void rotateReverse(double[][] a, int n, int row, int i, int j, double c, double s) {
        double aj, ai;
        for(int k = 0; k < n; k++) {
            aj = a[k][j];
            ai = a[k][i];

            a[k][j] = c * aj + s * ai;
            a[k][i] = -s * aj + c * ai;
        }
    }

    public void swapCols(double[][] a, int n, int i, int j) {
        double x;
        for(int k = 0; k < n; k++) {
            x = a[k][i];
            a[k][i] = a[k][j];
            a[k][j] = x;
        }
    }

    public void swapRows(double[][] a, int n, int i, int j) {
        double x;
        for(int k = 0; k < n; k++) {
            x = a[i][k];
            a[i][k] = a[j][k];
            a[j][k] = x;
        }
    }

    public double colNorm(double[][] a, int n, int col) {
        double ret = 0;
        for(int k = 0; k < n; k++) {
            ret += a[k][col]*a[k][col];
        }
        return ret;
    }


    public void triangulate(double[][] a, int n, int[] col_transp, int[] row_transp) {
        for(int j = 0; j < n-1; j++) {/*
            // Перестановка столбцов
            int maxIndex = j;
            double max = -1;
            double x;
            for(int k = j; k < n-1; k++) {
                x = colNorm(a, n, k);
                if (x > max) {
                    max = x;
                    maxIndex = k;
                }
            }
            col_transp[j] = maxIndex;
            swapCols(a, n, j, maxIndex);

            // Перестановка строк
            maxIndex = j + 1;
            max = -1;
            for(int k = j + 1; k < n; k++) {
                x = a[k][j];
                if (x > max) {
                    max = x;
                    maxIndex = k;
                }
            }
            row_transp[j+1] = maxIndex;
            swapRows(a, n, j+1, maxIndex);*/

            for(int i = j+1; i < n; i++) {
                rotate(a, n, j, i, j);
            }
        }
    }

    public void saveTransformationParams(double[][] a, int row, int col, double c, double s) {
        a[row][col] = c + Math.signum(s);
    }


    /**
     * @return 2-elemental array containing c and s
     */
    public double[] loadTransformationParams(double q) {
        boolean negativeS = q < 0;
        double c, s;

        if(negativeS) {
            c = q + 1;
            s = -Math.sqrt(1 - c*c);
        } else {
            c = q - 1;
            s = Math.sqrt(1 - c*c);
        }
        double[] ret = {c, s};
        return ret;
    }

    public void multiplicationErgonomic(double[][] a, int n, int[] col_transp, int[] row_transp) {
        double[] q = new double[n];

        for(int j = n-2; j >=0; j--) {
            for(int i = n-1; i >= j+1; i--) {
                q[i] = a[i][j];
                a[i][j] = 0;
            }
            // Осуществить поворот
            for(int i = n-1; i >= j+1; i--) {
                double[] params = loadTransformationParams(q[i]);
                double c = params[0];
                double s = params[1];
                rotateReverse(a, n, i, i, j, c, s);
            }/*
            // Поменять #строки местами
            swapCols(a, n, j+1, row_transp[j+1]);

            // Поменять #столбцы местами
            swapRows(a, n, j, col_transp[j]);*/
        }

    }

    public void invertMatrixErgonomic(double[][] a, int n) {

        Gen gen = new Gen();

        int[] col_transp = new int[n];
        int[] row_transp = new int[n];

        triangulate(a, n, col_transp, row_transp);

        //gen.print_matr(a, n);

        invertTriangle(a, n);

        //gen.print_matr(a, n);

        multiplicationErgonomic(a, n, col_transp, row_transp);

    }


}
