package calculation.stats;

import general.usefuls1.SortIdx_simple1_2;

public class BH_pval1 {

	private double[] pvals;
	
	public BH_pval1(double[] pvals){
		
		this.pvals = pvals;
		
		SortIdx_simple1_2 sidx = new SortIdx_simple1_2(pvals);
		
		int[] rank = sidx.getRank();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double[] pvalue_ex1 = { 0.3, 0.4, 0.5, 0.2, 0.4,  0.3, 0.1, 0.4, 0.9, 0.1 };
		
		BH_pval1 bhp1 = new BH_pval1(pvalue_ex1);
		
	}

}
