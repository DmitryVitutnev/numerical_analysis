package ru.omsu.imit.mgen.richardson;

public class Richardson {

    public static double[] solveSLAE(double[][] a, double[] f, double alpha, double beta, int r) {

        // I took formulas from here https://old.math.tsu.ru/EEResources/cm/text/5_11_1.htm

        int len = f.length;
        double[] x = new double[len];
        for(int i = 0; i < len; i++) {
            x[i] = 0;
        }
        double[] prevX = new double[len];

        double t0 = 2./(alpha+beta);
        double eta = alpha/beta;
        double rho0 = (1-eta)/(1+eta);
        double rho1 = (1-Math.sqrt(eta))/(1+Math.sqrt(eta));
        int s = (int) Math.pow(2, r);
        double[] t = new double[s];
        for(int k = 0; k < s; k++) {
            t[k] = calculateT(t0, rho0, k, len);
        }
        t = optimalSort(t, r);
        for(int i = 0; i < s; i++){
            copyArray(x, prevX);
            double[] temp = multiplyMatrixOnVector(a, prevX);
            addVector(x, temp, -1*t[i]);
            addVector(x, f, t[i]);
        }
        return x;
    }

    public static void copyArray(double[] from, double[] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }

    public static void copyArray(int[] from, int[] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }

    public static double[] multiplyMatrixOnVector(double[][] matrix, double[] vector) {
        double[] result = new double[vector.length];
        for(int i = 0; i < vector.length; i++) {
            result[i] = 0;
            for(int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    public static void addVector(double[] source, double[] addition, double koef) {
        for(int i = 0; i < source.length; i++) {
            source[i] = source[i] + koef * addition[i];
        }
    }

    public static double calculateT(double t0, double rho0, int k, int n) {
        double nu = Math.cos((2*k-1)*Math.PI/(2*n));
        return t0/(1+rho0*nu);
    }

    public static double[] optimalSort(double[] arrRet, int r) {

        int n = (int) Math.pow(2, r);
        int[] indices = new int[n];
        int[] temp = new int[n];
        for(int k = 1; k <= r; k++) {
            int s = (int) Math.pow(2, k);
            copyArray(indices, temp);
            for(int i = 0; i < s/2; i++) {
                indices[2*i] = temp[i];
                indices[2*i + 1] = s - 1 - temp[i];
            }
        }
        double[] result = new double[n];
        for(int i = 0; i < n; i++) {
            result[i] = arrRet[indices[i]];
        }
        return result;
    }

    public static double vectorNorm(double[] vector) {
        double norm = Math.abs(vector[0]);
        for(int i = 0; i < vector.length; i++) {
            norm = Math.max(norm, Math.abs(vector[i]));
        }
        return norm;
    }


}