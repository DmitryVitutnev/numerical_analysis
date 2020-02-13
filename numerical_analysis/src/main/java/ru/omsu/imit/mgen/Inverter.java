package ru.omsu.imit.mgen;

public class Inverter {

    public void rotate(double[][] a, int n, int col, int i, int j) {
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
    }

    public void triangulate(double[][] a, int n) {
        for(int j = 0; j < n-1; j++) {
            for(int i = j+1; i < n; i++) {
                rotate(a, n, j, i, j);
            }
        }
    }

    public void invertMatrix(double[][] a, int n) {




    }


}
