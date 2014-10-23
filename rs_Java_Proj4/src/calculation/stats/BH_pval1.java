package calculation.stats;

import general.usefuls1.SortIdx_simple1_2;

public class BH_pval1 {

	private double[] pvals;
	
	public BH_pval1(double[] pvals){
		
		this.pvals = pvals;
		
		double[] obh_pvals = new double[pvals.length];
		
		for(int i = 0;i < pvals.length;i ++){
			obh_pvals[i] = Double.NaN;
		}
		
		SortIdx_simple1_2 sidx = new SortIdx_simple1_2(pvals);
		
		int[] rank = sidx.getRank();
		
		double[] p_div_rk = new double[pvals.length];
		
		for(int i = 0;i < pvals.length;i ++){
			p_div_rk[i] = pvals[i] * rank[i] / pvals.length;
		}
		
		SortIdx_simple1_2 p_div_rk_sidx = new SortIdx_simple1_2(p_div_rk);
		int[] p_div_rk_sindices = p_div_rk_sidx.getSortIdx_raw();
		
		for(int i = 0;i < pvals.length;i ++){
			int careidx = p_div_rk_sindices[i];
			
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double[] pvalue_ex1 = { 0.3, 0.4, 0.5, 0.2, 0.4,  0.3, 0.1, 0.4, 0.9, 0.1 };
		
		BH_pval1 bhp1 = new BH_pval1(pvalue_ex1);
		
	}

}
