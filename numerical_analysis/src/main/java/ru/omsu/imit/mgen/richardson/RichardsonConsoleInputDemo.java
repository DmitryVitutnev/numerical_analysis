package ru.omsu.imit.mgen.richardson;

import java.util.Locale;
import java.util.Scanner;

public class RichardsonConsoleInputDemo {

    public static void main(String[] args) {



        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.ENGLISH);


        System.out.println("This program will approximately solve equation Ax=f");
        System.out.println("Enter matrix size");

        int n = sc.nextInt();

        System.out.println("Enter values of matrix A");

        double[][] a = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextDouble();
            }
        }

        System.out.println("Enter values of f");

        double[] f = new double[n];

        for (int i = 0; i < n; i++) {
            f[i] = sc.nextDouble();
        }

        System.out.println("Enter EPSILON");

        double epsilon = sc.nextDouble();


        double[] x = Richardson.solveSLAE(a, f, 4, epsilon);

        System.out.println("Solution is:");

        for (int i = 0; i < n; i++) {
            System.out.print("" + x[i] + " ");
        }

    }


}
