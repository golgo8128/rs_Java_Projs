package dorothea;

import dorothea.MatrixVectorMult;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Propagation1 {

	/* flow, pump information through network from source nodes (the restriction factors / host factors) 
	 * NO HIV interactions included here, only HUMANNET
	 * F(u)^t = alpha * W' * F(u)^t-1 + (1-alpha) * Y
	 * 
	 * W': adjmatrix / weightmatrix with entries normalized by row sums (degree-normalized or weight-normalized)
	 * W'(i,j) = W(i,j) / sqrt(D(i,i), D(j,j)) 
	 * D(i,i) = row sum_j: wij
	 * 
	 * Y = vector with prior knowledge, all restriction factors / host factors set to RNAi value, normalized to [0,1], the others set to 0
	 * 
	 * alpha > 0.5, apparently does not make any difference, thus set it to 0.8
	 * iterate and when everything is in steady-state add a round with alpha = 1
	 * 
	 * parameter settings:
	 * network unweighted based on HUMANNET
	 * 
	 */

	public static void main (String[] args) throws Exception
	{

		// alpha parameter
		double alpha = 0.8;

		HashSet<String> nodes, edges;
		double [][] adjmatrix;
		double[] diagonal, ft1, y, ft;

		nodes = new HashSet<String>();
		edges = new HashSet<String>();

		// get network nodes
		BufferedReader br = new BufferedReader(new FileReader("/cellar/users/rsaito/Desktop/BioGRID_PPI_scer1.tsv"));
		String line;
		while ((line = br.readLine()) != null)
		{
			nodes.add(line.split("\t")[0]);
			nodes.add(line.split("\t")[1]);
		}

		// nodes is a hashset without a proper ordering. so put all nodes in string[] to make sure you know the order.
		String[] node_order = new String[nodes.size()];
		int i = 0;
		Iterator<String> it = nodes.iterator(); 
		while (it.hasNext())
		{
			node_order[i] = it.next();
			i++;
		}
		System.out.println("nodes done");

		// get edges, use unweighted network, weights do not improve the results!!	
		br = new BufferedReader(new FileReader("/cellar/users/rsaito/Desktop/BioGRID_PPI_scer1.tsv"));
		while ((line =  br.readLine()) != null)
		{
			edges.add(line.split("\t")[0]+"_"+line.split("\t")[1]);
			edges.add(line.split("\t")[1]+"_"+line.split("\t")[0]);
		}
		System.out.println("edges done");

		adjmatrix = new double[nodes.size()][nodes.size()];
		for (i = 0; i != nodes.size(); i++)
		{
			for (int j = 0; j != nodes.size(); j++)
			{
				String p1 = node_order[i];
				String p2 = node_order[j];
				// edge exists
				if (edges.contains(p1+"_"+p2))
				{
					adjmatrix[i][j] = 1.0;
					adjmatrix[j][i] = 1.0;
				}
				else
				{
					adjmatrix[i][j] = 0.0;
					adjmatrix[j][i] = 0.0;
				}
			}
		}
		System.out.println("adjmatrix done");

		// compute the diagonal matrix, contains the row sums in (i,i), 0 else
		diagonal = new double[nodes.size()];
		for (i = 0; i != nodes.size(); i++)
		{
			double rowsum = 0;
			for (int x = 0; x != nodes.size(); x++)
			{
				rowsum += adjmatrix[i][x];
			}
			diagonal[i] = rowsum;
		}

		// and now compute W' 
		for (i = 0; i != nodes.size(); i++)
		{
			for (int j = 0; j != nodes.size(); j++)
			{
				double norm = Math.sqrt((diagonal[i]*diagonal[j]));
				adjmatrix[i][j] = adjmatrix[i][j] / norm;
			}
		}

		// get the lists of starting points
		HashMap<String, Double> start = new HashMap<String, Double>();
		br = new BufferedReader(new FileReader("/cellar/users/rsaito/Desktop/TP_Integ_LeeMV1.tsv"));
		while ((line = br.readLine()) != null)
		{
			start.put(line.split("\t")[0], Double.parseDouble(line.split("\t")[1]));
		}


		// now set Y
		// set all entries to the normalized inhibition rate, or 0
		ft1 = new double[nodes.size()];
		y = new double[nodes.size()];
		for (i = 0; i != nodes.size(); i++)
		{
			if (start.containsKey(node_order[i]))
			{
			ft1[i] = start.get(node_order[i]);
			y[i] = ft1[i] * (1.0 - alpha);
			}
			else
			{
				ft1[i] = 0.0;
				y[i] = 0.0;
			}
		}
		System.out.println("y vector done");

		// now compute the propagation...
		ft = new double[nodes.size()]; 
		while (true)
		{
			ft = MatrixVectorMult.add(MatrixVectorMult.mult(adjmatrix, ft1, alpha),y);
			// compare the L1 norms of ft and ft1 -> if less than 10^-6 break
			// else: set ft = pt1 and compute the next pt1
			double l1ft = 0.0, l1ft1 = 0.0;
			for (i = 0; i != nodes.size(); i++)
			{
				l1ft += Math.abs(ft[i]);
				l1ft1 += Math.abs(ft1[i]);
			}
			if (Math.abs(l1ft - l1ft1) < 0.0000001)
				break;
			else
				ft1 = ft;
		}
		System.out.println("transition done");

		// and now the final smoothing step with alpha = 1... (which means, no y added in the last step)
		ft = MatrixVectorMult.mult(adjmatrix, ft, 1.0);

		BufferedWriter bw = new BufferedWriter(new FileWriter("/cellar/users/rsaito/Desktop/tmp1"));
		for (i = 0; i != nodes.size(); i++)
		{
			bw.write(node_order[i]+"\t"+ft[i]+"\n");
		}

		bw.close();

		try 
		{ 
			Process p;
			p = Runtime.getRuntime().exec("sort -r -k 2 -g /cellar/users/rsaito/Desktop/tmp1 -o /cellar/users/rsaito/Desktop/propagation_output");
			p.waitFor(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream())); 
			line=reader.readLine(); 
			while(line != null) 
			{ 
				line=reader.readLine(); 
			} 
		} 
		catch(Exception e1) {e1.printStackTrace();}

		try 
		{ 
			Process p = Runtime.getRuntime().exec("rm /cellar/users/rsaito/Desktop/tmp1"); 
			p.waitFor(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream())); 
			line=reader.readLine(); 
			while(line != null) 
			{ 
				line=reader.readLine(); 
			} 

		} 
		catch(Exception e1) {e1.printStackTrace();}
	}
}

