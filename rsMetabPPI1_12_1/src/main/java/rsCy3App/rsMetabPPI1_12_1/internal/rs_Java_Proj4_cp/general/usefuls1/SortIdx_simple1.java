package org.cytoscape.rsCy3App.rsMetabPPI1_12_1.internal.rs_Java_Proj4_cp.general.usefuls1;

import java.util.Arrays;
import java.util.Comparator;

public class SortIdx_simple1 {
	
	public static Integer[] sortidx(final double[] data){
		
		Integer[] idx = new Integer[data.length];
		
		for(int i = 0;i < data.length;i ++){
			idx[i] = i;
		}
		
		Arrays.sort(idx, new Comparator<Integer>() {
			@Override public int compare(final Integer o1, final Integer o2) {
				return Double.compare(data[o1], data[o2]);
			}
		});
		
		for(int i = 0;i < data.length;i ++){
			idx[i] += 1;
		}
		
		return idx;
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double[] data = { 1.7f, -0.3f,  2.1f,  0.5f, 1.7f };

		Integer[] idx = sortidx(data);

		for(int e_idx: idx){
			System.out.printf("%d\n", e_idx);
		}

	}

}
