package ru.omsu.imit.mgen.richardson;

import org.junit.Test;
import ru.omsu.imit.mgen.Gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RichardsonTest {

    private String directory = "results/richardson/";


    @Test
    public void symmetricTest() throws IOException {
        int n = 100;
        double alpha = 1.;
        double beta = 1.;
        PrintWriter writer = new PrintWriter(new FileWriter(new File(directory + "symmetric.csv"), false));

        writer.println("" + "alpha" + ";" + "beta" + ";" + "||A||" + ";" + "||A-1||" + ";" + "v(A)" + ";" + "||z||" + ";" + "ksi" + ";" + "||r||" + ";" + "rho");
        writer.flush();

        int count = 0;
        while (true) {


            double[][] a = new double[n][n];
            double[][] a_inv = new double[n][n];
            Gen g = new Gen();
            g.mygen(a, a_inv, n, alpha, beta, 1, 2, 0, 1);
            double[] xReal = new double[n];
            for (int i = 0; i < n; i++) {
                xReal[i] = Math.random();
            }
            double[] f = Richardson.multiplyMatrixOnVector(a, xReal);
            double[] x = Richardson.solveSLAE(a, f, alpha, beta, 10);

            double normA = g.matr_inf_norm(a, n);
            double normAr = g.matr_inf_norm(a_inv, n);
            double vA = normA * normAr;
            double[] z = new double[n];
            for (int i = 0; i < n; i++) z[i] = 0.;
            Richardson.addVector(z, x, 1);
            Richardson.addVector(z, xReal, -1);
            double normZ = Richardson.vectorNorm(z);
            double ksi = normZ / Richardson.vectorNorm(xReal);
            double[] r = new double[n];
            for (int i = 0; i < n; i++) r[i] = 0.;
            Richardson.addVector(r, Richardson.multiplyMatrixOnVector(a, x), 1);
            Richardson.addVector(r, f, -1);
            double normR = Richardson.vectorNorm(r);
            double rho = normR / Richardson.vectorNorm(f);

            writer.printf("%e;%e;%e;%e;%e;%e;%e;%e;%e\n", alpha, beta, normA, normAr, vA, normZ, ksi, normR, rho);
            writer.flush();

            if (ksi > 10 || count >= 10) {
                break;
            }

            beta *= 10.;
            count++;
        }

        writer.println("" + "alpha" + ";" + "beta" + ";" + "||A||" + ";" + "||A-1||" + ";" + "v(A)" + ";" + "||z||" + ";" + "ksi" + ";" + "||r||" + ";" + "rho");
        writer.flush();

        n = 100;
        alpha = 1.;
        beta = 1.;

        count = 0;
        while (true) {

            double[][] a = new double[n][n];
            double[][] a_inv = new double[n][n];
            Gen g = new Gen();
            g.mygen(a, a_inv, n, alpha, beta, 1, 2, 0, 1);
            double[] xReal = new double[n];
            for (int i = 0; i < n; i++) {
                xReal[i] = Math.random();
            }
            double[] f = Richardson.multiplyMatrixOnVector(a, xReal);
            double[] x = Richardson.solveSLAE(a, f, alpha, beta, 10);

            double normA = g.matr_inf_norm(a, n);
            double normAr = g.matr_inf_norm(a_inv, n);
            double vA = normA * normAr;
            double[] z = new double[n];
            for (int i = 0; i < n; i++) z[i] = 0.;
            Richardson.addVector(z, x, 1);
            Richardson.addVector(z, xReal, -1);
            double normZ = Richardson.vectorNorm(z);
            double ksi = normZ / Richardson.vectorNorm(xReal);
            double[] r = new double[n];
            for (int i = 0; i < n; i++) r[i] = 0.;
            Richardson.addVector(r, Richardson.multiplyMatrixOnVector(a, x), 1);
            Richardson.addVector(r, f, -1);
            double normR = Richardson.vectorNorm(r);
            double rho = normR / Richardson.vectorNorm(f);

            writer.printf("%e;%e;%e;%e;%e;%e;%e;%e;%e\n", alpha, beta, normA, normAr, vA, normZ, ksi, normR, rho);
            writer.flush();

            if (ksi > 10 || count >= 10) {
                break;
            }

            alpha *= .1;
            count++;
        }


    }


    @Test
    public void simpleTest() throws IOException {
        int n = 100;
        double alpha = 1.;
        double beta = 1.;
        PrintWriter writer = new PrintWriter(new FileWriter(new File(directory + "simple.csv"), false));

        writer.println("" + "alpha" + ";" + "beta" + ";" + "||A||" + ";" + "||A-1||" + ";" + "v(A)" + ";" + "||z||" + ";" + "ksi" + ";" + "||r||" + ";" + "rho");
        writer.flush();

        int count = 0;
        while (true) {


            double[][] a = new double[n][n];
            double[][] a_inv = new double[n][n];
            Gen g = new Gen();
            g.mygen(a, a_inv, n, alpha, beta, 1, 2, 1, 1);
            double[] xReal = new double[n];
            for (int i = 0; i < n; i++) {
                xReal[i] = Math.random();
            }
            double[] f = Richardson.multiplyMatrixOnVector(a, xReal);
            double[] x = Richardson.solveSLAE(a, f, alpha, beta, 10);

            double normA = g.matr_inf_norm(a, n);
            double normAr = g.matr_inf_norm(a_inv, n);
            double vA = normA * normAr;
            double[] z = new double[n];
            for (int i = 0; i < n; i++) z[i] = 0.;
            Richardson.addVector(z, x, 1);
            Richardson.addVector(z, xReal, -1);
            double normZ = Richardson.vectorNorm(z);
            double ksi = normZ / Richardson.vectorNorm(xReal);
            double[] r = new double[n];
            for (int i = 0; i < n; i++) r[i] = 0.;
            Richardson.addVector(r, Richardson.multiplyMatrixOnVector(a, x), 1);
            Richardson.addVector(r, f, -1);
            double normR = Richardson.vectorNorm(r);
            double rho = normR / Richardson.vectorNorm(f);

            writer.printf("%e;%e;%e;%e;%e;%e;%e;%e;%e\n", alpha, beta, normA, normAr, vA, normZ, ksi, normR, rho);
            writer.flush();

            if (ksi > 10 || count >= 10) {
                break;
            }

            beta *= 10.;
            count++;
        }

        writer.println("" + "alpha" + ";" + "beta" + ";" + "||A||" + ";" + "||A-1||" + ";" + "v(A)" + ";" + "||z||" + ";" + "ksi" + ";" + "||r||" + ";" + "rho");
        writer.flush();

        n = 100;
        alpha = 1.;
        beta = 1.;

        count = 0;
        while (true) {

            double[][] a = new double[n][n];
            double[][] a_inv = new double[n][n];
            Gen g = new Gen();
            g.mygen(a, a_inv, n, alpha, beta, 1, 2, 1, 1);
            double[] xReal = new double[n];
            for (int i = 0; i < n; i++) {
                xReal[i] = Math.random();
            }
            double[] f = Richardson.multiplyMatrixOnVector(a, xReal);
            double[] x = Richardson.solveSLAE(a, f, alpha, beta, 10);

            double normA = g.matr_inf_norm(a, n);
            double normAr = g.matr_inf_norm(a_inv, n);
            double vA = normA * normAr;
            double[] z = new double[n];
            for (int i = 0; i < n; i++) z[i] = 0.;
            Richardson.addVector(z, x, 1);
            Richardson.addVector(z, xReal, -1);
            double normZ = Richardson.vectorNorm(z);
            double ksi = normZ / Richardson.vectorNorm(xReal);
            double[] r = new double[n];
            for (int i = 0; i < n; i++) r[i] = 0.;
            Richardson.addVector(r, Richardson.multiplyMatrixOnVector(a, x), 1);
            Richardson.addVector(r, f, -1);
            double normR = Richardson.vectorNorm(r);
            double rho = normR / Richardson.vectorNorm(f);

            writer.printf("%e;%e;%e;%e;%e;%e;%e;%e;%e\n", alpha, beta, normA, normAr, vA, normZ, ksi, normR, rho);
            writer.flush();

            if (ksi > 10 || count >= 10) {
                break;
            }

            alpha *= .1;
            count++;
        }


    }


    @Test
    public void jordanTest() throws IOException {
        int n = 100;
        double alpha = 1.;
        double beta = 1.;
        PrintWriter writer = new PrintWriter(new FileWriter(new File(directory + "jordan.csv"), false));

        writer.println("" + "alpha" + ";" + "beta" + ";" + "||A||" + ";" + "||A-1||" + ";" + "v(A)" + ";" + "||z||" + ";" + "ksi" + ";" + "||r||" + ";" + "rho");
        writer.flush();

        int count = 0;
        while (true) {


            double[][] a = new double[n][n];
            double[][] a_inv = new double[n][n];
            Gen g = new Gen();
            g.mygen(a, a_inv, n, alpha, beta, 1, 2, 2, 1);
            double[] xReal = new double[n];
            for (int i = 0; i < n; i++) {
                xReal[i] = Math.random();
            }
            double[] f = Richardson.multiplyMatrixOnVector(a, xReal);
            double[] x = Richardson.solveSLAE(a, f, alpha, beta, 10);

            double normA = g.matr_inf_norm(a, n);
            double normAr = g.matr_inf_norm(a_inv, n);
            double vA = normA * normAr;
            double[] z = new double[n];
            for (int i = 0; i < n; i++) z[i] = 0.;
            Richardson.addVector(z, x, 1);
            Richardson.addVector(z, xReal, -1);
            double normZ = Richardson.vectorNorm(z);
            double ksi = normZ / Richardson.vectorNorm(xReal);
            double[] r = new double[n];
            for (int i = 0; i < n; i++) r[i] = 0.;
            Richardson.addVector(r, Richardson.multiplyMatrixOnVector(a, x), 1);
            Richardson.addVector(r, f, -1);
            double normR = Richardson.vectorNorm(r);
            double rho = normR / Richardson.vectorNorm(f);

            writer.printf("%e;%e;%e;%e;%e;%e;%e;%e;%e\n", alpha, beta, normA, normAr, vA, normZ, ksi, normR, rho);
            writer.flush();

            if (ksi > 10 || count >= 10) {
                break;
            }

            beta *= 10.;
            count++;
        }

        writer.println("" + "alpha" + ";" + "beta" + ";" + "||A||" + ";" + "||A-1||" + ";" + "v(A)" + ";" + "||z||" + ";" + "ksi" + ";" + "||r||" + ";" + "rho");
        writer.flush();

        n = 100;
        alpha = 1.;
        beta = 1.;

        count = 0;
        while (true) {

            double[][] a = new double[n][n];
            double[][] a_inv = new double[n][n];
            Gen g = new Gen();
            g.mygen(a, a_inv, n, alpha, beta, 1, 2, 2, 1);
            double[] xReal = new double[n];
            for (int i = 0; i < n; i++) {
                xReal[i] = Math.random();
            }
            double[] f = Richardson.multiplyMatrixOnVector(a, xReal);
            double[] x = Richardson.solveSLAE(a, f, alpha, beta, 10);

            double normA = g.matr_inf_norm(a, n);
            double normAr = g.matr_inf_norm(a_inv, n);
            double vA = normA * normAr;
            double[] z = new double[n];
            for (int i = 0; i < n; i++) z[i] = 0.;
            Richardson.addVector(z, x, 1);
            Richardson.addVector(z, xReal, -1);
            double normZ = Richardson.vectorNorm(z);
            double ksi = normZ / Richardson.vectorNorm(xReal);
            double[] r = new double[n];
            for (int i = 0; i < n; i++) r[i] = 0.;
            Richardson.addVector(r, Richardson.multiplyMatrixOnVector(a, x), 1);
            Richardson.addVector(r, f, -1);
            double normR = Richardson.vectorNorm(r);
            double rho = normR / Richardson.vectorNorm(f);

            writer.printf("%e;%e;%e;%e;%e;%e;%e;%e;%e\n", alpha, beta, normA, normAr, vA, normZ, ksi, normR, rho);
            writer.flush();

            if (ksi > 10 || count >= 10) {
                break;
            }

            alpha *= .1;
            count++;
        }


    }


}
