package org.cytoscape.rsCy3App.rsMetabPPI1_12_1.internal.rs_Java_Proj4_cp.general.usefuls1;

import java.util.Arrays;
import java.util.Comparator;

public class SortIdx_simple1_2 {
	
	private final double[] ivalues;
	private Integer[] indices;
	
	public SortIdx_simple1_2(final double[] ivalues){
		
		this.ivalues = ivalues;
		
		indices = new Integer[ivalues.length];
		
		for(int i = 0;i < ivalues.length;i ++){
			indices[i] = i;
		}
		
		Arrays.sort(indices, new Comparator<Integer>() {
			@Override public int compare(final Integer o1, final Integer o2) {
				return Double.compare(ivalues[o1], ivalues[o2]);
			}
		});
				
	}
	
	public int[] getSortIdx_raw(){
		
		int[] oidx = new int[ivalues.length];
		
		for(int i = 0;i < ivalues.length;i ++){
			oidx[i] = (int)(indices[i]);
		}
		
		return oidx;
		
	}
	
	
	public int[] getSortIdx(){
				
		int[] oidx = new int[ivalues.length];
		
		for(int i = 0;i < ivalues.length;i ++){
			oidx[i] = (int)(indices[i] + 1);
		}
		
		return oidx;
	
	}
	
	public int[] getRank(){
		
		int[] orank = new int[ivalues.length];
		
		int i = 1;
		for(int eidx: indices){
			orank[eidx] = i;
			i ++;
		}		
		
		return orank;
		
	}
	
	public int[] getRank_TieLower(){
		
		int[] orank = new int[ivalues.length];
		int ilower = ivalues.length;
		double prev_val = Double.NaN;
		
		for(int i = ivalues.length-1;i >=0;i --){
			int idx    = indices[i];
			double val = ivalues[idx];
			if(prev_val == val){
				orank[idx] = ilower+1;
			} else {
				orank[idx] = i+1;
				ilower = i;
			}
			prev_val = val;

		}		
		
		return orank;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double[] value1 = { 1.7, -0.3,  2.1,  0.5, 1.7, 2.9, 2.9, 3.7, 2.9 };
		
		SortIdx_simple1_2 sidx = new SortIdx_simple1_2(value1);
		int[] out;
		
		out = sidx.getSortIdx();

		for(int eachnum: out){
			System.out.printf("%d\n", eachnum);
		}

		System.out.println("---");
		
		out = sidx.getRank();

		for(int eachnum: out){
			System.out.printf("%d\n", eachnum);
		}		

		System.out.println("---");
		
		out = sidx.getRank_TieLower();

		for(int eachnum: out){
			System.out.printf("%d\n", eachnum);
		}				
		
	}

}
