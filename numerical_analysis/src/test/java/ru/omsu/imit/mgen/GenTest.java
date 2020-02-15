package ru.omsu.imit.mgen;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GenTest 
{

	private int N = 150;
	private double ALPHA = 1.;
	private double BETA  = 1.e+3;

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


		Inverter inverter = new Inverter();
		inverter.invertMatrixErgonomic(arr1,3);

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


		Inverter inverter = new Inverter();
		inverter.invertMatrixErgonomic(arr,3);

		g.print_matr(arr,3);

		double[][] identity = new double[3][3];
		g.matr_mul(arr, arrInv, identity, 3);
		g.print_matr(identity,3);
		System.out.println("------------------------------------");
	}

	@Test
	public void Test1()
	{
		System.out.println("Test1");
		int n = N;
		double alpha = ALPHA;
		double beta  = BETA;

		double[][] a = new double[n][n];

		double[][] a_inv = new double[n][n];

		Gen g = new Gen();

		g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
		//	g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
		//	g.mygen ( a, a_inv, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		g.print_matr(a,n);
		g.print_matr(a_inv,n);

		Inverter inverter = new Inverter();

		inverter.invertMatrixErgonomic(a, n);

		g.print_matr(a,n);

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

		double[][] a_inv = new double[n][n];

		Gen g = new Gen();

		//g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
		g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
		//	g.mygen ( a, a_inv, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		g.print_matr(a,n);
		g.print_matr(a_inv,n);

		Inverter inverter = new Inverter();

		inverter.invertMatrixErgonomic(a, n);

		g.print_matr(a,n);

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

		double[][] a_inv = new double[n][n];

		Gen g = new Gen();

		//g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
		//g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
		g.mygen ( a, a_inv, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		g.print_matr(a,n);
		g.print_matr(a_inv,n);

		Inverter inverter = new Inverter();

		inverter.invertMatrixErgonomic(a, n);

		g.print_matr(a,n);

		System.out.println(g.matr_inf_norm(a_inv, n));
		System.out.println(g.matr_inf_norm(a, n));

		System.out.println("------------------------------------");
	}

}
