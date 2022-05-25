
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import rsJava.MassSpec.*;

public class Test_RS_MassSpectra_simple1_2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Float[] mzs0 = { 1.1f, 2.2f, 3.3f, 4.4f, 5.5f };
		Long[] intsts0 = { 1L, 7L, 3L, 4L, 5L };

		Float[] mzs1 = { 1.2f, 2.3f, 3.4f, 4.5f, 5.6f, 6.5f, 6.7f };
		Long[] intsts1 = { 6L, 2L, 3L, 4L, 5L, 7L, 6L };

		Float[] mzs2 = {  0.2f, 2.5f, 3.4f };
		Long[] intsts2 = { 2L, 3L, 1L };

		Float[] mzs3 = {  0.2f, 2.1f, 3.6f };
		Long[] intsts3 = { 2L, 6L, 4L };
		
		Float[] mzs4 = {  0.2f, 2.5f, 3.4f, 3.5f, 3.8f };
		Long[] intsts4 = { 2L, 3L, 1L, 3L, 7L };
		
		Float[] mts = { 0.1f, 0.2f, 0.3f, 0.5f, 0.9f };
				
		RS_MassSpectra_simple1_2<Float, Float, Long> multi_ms
			= new RS_MassSpectra_simple1_2<Float, Float, Long>();
		
		multi_ms.add_ms(mts[ 0 ], mzs0, intsts0);
		multi_ms.add_ms(mts[ 1 ], mzs1, intsts1);		
		multi_ms.add_ms(mts[ 2 ], mzs2, intsts2);
		multi_ms.add_ms(mts[ 3 ], mzs3, intsts3);
		multi_ms.add_ms(mts[ 4 ], mzs4, intsts4);
		
		Path tmpfile = 
				Paths.get(System.getenv("RS_TMP_DIR")).
					resolve("rs_MSpectra").resolve("test3.rsmspra");
		multi_ms.output_to_file(tmpfile, 256);
				
		int relposs_mzs_starts[] = multi_ms.relposs_mzs_starts();
		int relposs_mzs_ends[] = multi_ms.relposs_mzs_ends();
		int relposs_intsts_starts[] = multi_ms.relposs_intsts_starts();
		int relposs_intsts_ends[] = multi_ms.relposs_intsts_ends();
		
		for(int i = 0; i < relposs_mzs_starts.length;i ++) {
		
			int[] relposs = new int[ 4 ];
			relposs[0] = relposs_mzs_starts[i];
			relposs[1] = relposs_mzs_ends[i];
			relposs[2] = relposs_intsts_starts[i];
			relposs[3] = relposs_intsts_ends[i];
			
			System.out.println(Arrays.stream(relposs)
					.mapToObj(String::valueOf)
					.collect(Collectors.joining(", ")));
			
		}
		System.out.println(tmpfile);
		
	}

}
