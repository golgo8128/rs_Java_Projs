package testFolder.misc1;

import java.util.Arrays;
import java.util.Comparator;

public class SortIdx_test1 {

	public static void main(String[] args) {

		
		final Integer[] idx = { 0, 1, 2, 3, 4 };
		final float[] data = { 1.7f, -0.3f,  2.1f,  0.5f, 1.7f };

		Arrays.sort(idx, new Comparator<Integer>() {
		    @Override public int compare(final Integer o1, final Integer o2) {
		        return Float.compare(data[o1], data[o2]);
		    }
		});
		
		for(int e_idx: idx){
			System.out.printf("%d\n", e_idx);
		}
	}

}
