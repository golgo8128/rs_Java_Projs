package Prop1;


public class MatrixVectorMult 
{
	public static double[] mult (double[][] matrix, double[] vector, double scalar)
	{
		double[] result = new double[vector.length];
		
		for (int i = 0; i != vector.length; i++)
		{
			double res = 0.0;
			for (int j = 0; j != vector.length; j++)
			{
				res += matrix[i][j] * scalar * vector[j];
			}
			result[i] = res;
		}
		
		return result;
	}
	
	public static double[][] multScalar (double[][] matrix, double scalar)
	{
		for (int i = 0; i != matrix.length; i++)
		{
			for (int j = 0; j != matrix.length; j++)
			{
				matrix[i][j] = matrix[i][j] * scalar;
			}
		}
		
		return matrix;
	}

	
	public static double[] add(double[] mult, double[] reset) 
	{
		for (int i = 0; i != reset.length; i++)
		{
			mult[i] = mult[i] + reset[i];
		}
		return mult;
	}
	
	public static double[][] subMatrix(double[][] a, double[][] b)
	{
		for (int i = 0; i != a.length; i++)
		{
			for (int j = 0; j != a.length; j++)
			{
				a[i][j] = a[i][j] - b[i][j];
			}
		}
		
		return a;
	}
}