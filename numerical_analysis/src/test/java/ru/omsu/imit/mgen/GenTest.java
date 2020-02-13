package ru.omsu.imit.mgen;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GenTest 
{

	private int N = 3;
	private double ALPHA = 1.;
	private double BETA  = 1.e+3;

	@Test
	public void GenTest()
	{
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
	}

	@Test
	public void InvertTest()
	{
		Gen g = new Gen();

		//g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 0, 1 ); // симметричная
		//	g.mygen ( a, a_inv, n, alpha, beta, 1, 2, 1, 1 ); //проостой структуры
		//	g.mygen ( a, a_inv, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

		double[][] arr = {{1,2,3},{4,5,6},{7,8,10}};
		double[][] arrInv = {{1,2,3},{4,5,6},{7,8,10}};

		g.print_matr(arr,3);


		Inverter inverter = new Inverter();
		inverter.invertMatrix(arr,3);

		g.print_matr(arr,3);

		double[][] identity = new double[3][3];
		g.matr_mul(arr, arrInv, identity, 3);
		g.print_matr(identity,3);
	}

}
