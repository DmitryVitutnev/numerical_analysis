package ru.omsu.imit.mgen;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GenTest 
{

	private int N = 100;
	private double ALPHA = 1.;
	private double BETA  = 1.;

	@Test
	public void GenTest()
	{
		System.out.println("GenTest");
		int n = N;
		double alpha = ALPHA;
		double beta  = BETA;

		double[][] a = new double[n][];
		for (int i = 0; i < n; i++)	a[i] = new double[n];

		double[][] a_inv = new double[n][];
		for (int i = 0; i < n; i++)	a_inv[i] = new double[n];

		Gen g = new Gen();

		g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
	//	g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
	//	g.mygen ( a, a_inv, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		g.print_matr(a,n);
		g.print_matr(a_inv,n);
		System.out.println("------------------------------------");
	}

	@Test
	public void VovaTest()
	{
		System.out.println("VovaTest");
		Gen g = new Gen();

		//g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
		//	g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
		//	g.mygen ( a, a_inv, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		double[][] arr1 = {{12,13,14},{3,6,5},{76,76,46}};
		double[][] arrInv1 = {{12,13,14},{3,6,5},{76,76,46}};

		g.print_matr(arr1,3);


		Inverter.invertMatrix(arr1,3);

		g.print_matr(arr1,3);

		/*double[][] identity = new double[3][3];
		g.matr_mul(arr1, arrInv1, identity, 3);
		g.print_matr(identity,3);*/
		System.out.println("------------------------------------");
	}

	@Test
	public void InvertErgonomicTest()
	{
		System.out.println("InvertErgonomicTest");
		Gen g = new Gen();

		//g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
		//	g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
		//	g.mygen ( a, a_inv, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		double[][] arr = {{1,2,3},{4,5,6},{7,8,10}};
		double[][] arrInv = {{1,2,3},{4,5,6},{7,8,10}};

		g.print_matr(arr,3);


		Inverter.invertMatrix(arr,3);

		g.print_matr(arr,3);

		double[][] identity = new double[3][3];
		g.matr_mul(arr, arrInv, identity, 3);
		g.print_matr(identity,3);
		System.out.println("------------------------------------");
	}

	@Test
	public void UniteAllTests() {
		ALPHA = 1.;
		BETA = 1.;
		for(int i = 0; i <= 10; i++) {
			Test1();
			BETA *= 10;
		}
		BETA = 1.;
		for(int i = 0; i <= 10; i++) {
			Test1();
			ALPHA /= 10;
		}

		ALPHA = 1.;
		BETA = 1.;
		for(int i = 0; i <= 10; i++) {
			Test2();
			BETA *= 10;
		}
		BETA = 1.;
		for(int i = 0; i <= 10; i++) {
			Test2();
			ALPHA /= 10;
		}

		ALPHA = 1.;
		BETA = 1.;
		for(int i = 0; i <= 10; i++) {
			Test3();
			BETA *= 10;
		}
		BETA = 1.;
		for(int i = 0; i <= 10; i++) {
			Test3();
			ALPHA /= 10;
		}



	}

	@Test
	public void Test1()
	{
		System.out.println("Test1");
		int n = N;
		double alpha = ALPHA;
		double beta  = BETA;


		double[][] a = new double[n][n];

		double[][] a_expect = new double[n][n];

		Gen g = new Gen();

		g.mygen ( a, a_expect, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
		//g.mygen ( a, a_expect, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
		//g.mygen ( a, a_expect, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		double[][] a_old = new double[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				a_old[i][j] = a[i][j];
			}
		}


		Inverter.invertMatrix(a, n);
		double normA = g.matr_inf_norm(a_old, n);
		double normAr = g.matr_inf_norm(a_expect, n);
		double vA = normA * normAr;
		double normZ = g.matr_inf_norm(Inverter.subtact(a, a_expect, n), n);
		double ksi = normZ / normAr;
		double[][] mulAAr = new double[n][n];
		g.matr_mul(a_old, a, mulAAr, n);
		double normR = g.matr_inf_norm(Inverter.subtact(mulAAr, Inverter.createIdentityMatrix(n), n), n);


		System.out.printf("alpha = %e\n", alpha);
		System.out.printf("beta = %e\n", beta);
		System.out.printf("||A|| = %e\n", normA);
		System.out.printf("||A-1|| = %e\n", normAr);
		System.out.printf("v(A) = %e\n", vA);
		System.out.printf("||z|| = %e\n", normZ);
		System.out.printf("ksi = %e\n", ksi);
		System.out.printf("||r|| = %e\n", normR);

		System.out.println("------------------------------------");
	}

	@Test
	public void Test2()
	{
		System.out.println("Test2");
		int n = N;
		double alpha = ALPHA;
		double beta  = BETA;


		double[][] a = new double[n][n];

		double[][] a_expect = new double[n][n];

		Gen g = new Gen();

		//g.mygen ( a, a_expect, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
		g.mygen ( a, a_expect, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
		//g.mygen ( a, a_expect, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		double[][] a_old = new double[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				a_old[i][j] = a[i][j];
			}
		}


		Inverter.invertMatrix(a, n);
		double normA = g.matr_inf_norm(a_old, n);
		double normAr = g.matr_inf_norm(a_expect, n);
		double vA = normA * normAr;
		double normZ = g.matr_inf_norm(Inverter.subtact(a, a_expect, n), n);
		double ksi = normZ / normAr;
		double[][] mulAAr = new double[n][n];
		g.matr_mul(a_old, a, mulAAr, n);
		double normR = g.matr_inf_norm(Inverter.subtact(mulAAr, Inverter.createIdentityMatrix(n), n), n);


		System.out.printf("alpha = %e\n", alpha);
		System.out.printf("beta = %e\n", beta);
		System.out.printf("||A|| = %e\n", normA);
		System.out.printf("||A-1|| = %e\n", normAr);
		System.out.printf("v(A) = %e\n", vA);
		System.out.printf("||z|| = %e\n", normZ);
		System.out.printf("ksi = %e\n", ksi);
		System.out.printf("||r|| = %e\n", normR);

		System.out.println("------------------------------------");
	}

	@Test
	public void Test3()
	{
		System.out.println("Test3");
		int n = N;
		double alpha = ALPHA;
		double beta  = BETA;


		double[][] a = new double[n][n];

		double[][] a_expect = new double[n][n];

		Gen g = new Gen();

		//g.mygen ( a, a_expect, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
		//g.mygen ( a, a_expect, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
		g.mygen ( a, a_expect, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		double[][] a_old = new double[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				a_old[i][j] = a[i][j];
			}
		}


		Inverter.invertMatrix(a, n);
		double normA = g.matr_inf_norm(a_old, n);
		double normAr = g.matr_inf_norm(a_expect, n);
		double vA = normA * normAr;
		double normZ = g.matr_inf_norm(Inverter.subtact(a, a_expect, n), n);
		double ksi = normZ / normAr;
		double[][] mulAAr = new double[n][n];
		g.matr_mul(a_old, a, mulAAr, n);
		double normR = g.matr_inf_norm(Inverter.subtact(mulAAr, Inverter.createIdentityMatrix(n), n), n);


		System.out.printf("alpha = %e\n", alpha);
		System.out.printf("beta = %e\n", beta);
		System.out.printf("||A|| = %e\n", normA);
		System.out.printf("||A-1|| = %e\n", normAr);
		System.out.printf("v(A) = %e\n", vA);
		System.out.printf("||z|| = %e\n", normZ);
		System.out.printf("ksi = %e\n", ksi);
		System.out.printf("||r|| = %e\n", normR);

		System.out.println("------------------------------------");
	}

}
