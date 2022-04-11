package rsCy3App.rsMetabPPI1_13_1.internal.rs_Java_Proj4_cp.calculation.stats;

import rsCy3App.rsMetabPPI1_13_1.internal.rs_Java_Proj4_cp.general.usefuls1.SortIdx_simple1_2;

public class PValsCorrect1 {

	private double[] pvals;
	
	public PValsCorrect1(double[] pvals){
		
		this.pvals = pvals;
		
	}
	
	public double[] calc_BH_pvals(){
	
		double[] obh_pvals = new double[pvals.length];
		
		for(int i = 0;i < pvals.length;i ++){
			obh_pvals[i] = Double.NaN;
		}
		
		SortIdx_simple1_2 sidx = new SortIdx_simple1_2(pvals);
		
		int[] sindices_raw = sidx.getSortIdx_raw();
		int[] rank = sidx.getRank_TieLower();
		
		double[] p_mlt_rk = new double[pvals.length];
		
		for(int i = 0;i < pvals.length;i ++){
			p_mlt_rk[i] = pvals[i] / rank[i] * pvals.length;
		}
		
		SortIdx_simple1_2 p_mlt_rk_sidx = new SortIdx_simple1_2(p_mlt_rk);
		int[] p_mlt_rk_sindices = p_mlt_rk_sidx.getSortIdx_raw();
		
		
		for(int i = 0;i < pvals.length;i ++){
			int careidx = p_mlt_rk_sindices[i];
			for(int j = 0;j < rank[careidx];j ++){
				int caresub_idx = sindices_raw[j];
				if(Double.isNaN(obh_pvals[caresub_idx])){
					obh_pvals[caresub_idx] = p_mlt_rk[careidx];
				}

			}
			
		}
		
		return obh_pvals;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double[] pvalue_ex1 = { 0.3, 0.4, 0.5, 0.2, 0.4,  0.3, 0.1, 0.4, 0.9, 0.1,
				                0.7, 0.4, 0.1, 0.3, 0.2,  0.2, 0.2, 0.2, 0.7, 0.7 };
		
		PValsCorrect1 bhp1 = new PValsCorrect1(pvalue_ex1);
		
		double[] bh_pvalues1 = bhp1.calc_BH_pvals();
		
		for(double pval: bh_pvalues1){
			System.out.printf("%f\n", pval);
		}
		
	}

}
