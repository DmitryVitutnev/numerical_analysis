package ru.omsu.imit.mgen.richardson;

import ru.omsu.imit.mgen.Gen;

public class RichardsonDemo {

    public static void main(String[] args) {

        /*double[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8,
                        9, 10, 11, 12, 13, 14, 15};

        Richardson.optimalSort(arr, 4);

        for(int i=0; i<16; i++) {
            System.out.print("" + arr[i] + " ");
        }

        double[][] a = {{1,2},{2,1}};
        double[] f = {3., 4.5};

        double[] result = Richardson.solveSLAE(a, f, 1, 3, 100);

        for(int i=0; i<result.length; i++) {
            System.out.print("" + result[i] + " ");
        }*/
        test();
    }


    public static void test() {
        int n = 100;
        double alpha = 1.;
        double beta = 1.e+5;

        double[][] a = new double[n][];
        for (int i = 0; i < n; i++) a[i] = new double[n];

        double[][] a_inv = new double[n][];
        for (int i = 0; i < n; i++) a_inv[i] = new double[n];

        Gen g = new Gen();
        g.mygen(a, a_inv, n, alpha, beta, 1, 2, 1, 1);

        double[] x = new double[n];
        for (int i = 0; i < n; i++) x[i] = Math.random();

        double[] f = new double[n];

        for (int i = 0; i < n; i++) {
            f[i] = 0;
            for (int j = 0; j < n; j++) {
                f[i] += a[i][j] * x[j];
            }
        }


        double[] xResult = Richardson.solveSLAE(a, f, 1, 1.e-6);



        double[] difference = new double[n];

        for (int i = 0; i < n; i++) difference[i] = x[i] - xResult[i];

        for (int i = 0; i < n; i++) {
            System.out.println("" + x[i] + " " + xResult[i] + " " + difference[i]);
        }

    }

}
