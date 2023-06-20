package OldVersions;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


import RS.MassSpec.*;

public class Test_RS_MassSpectra_simple1_4 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Float[] mzs0 = { 1.1f, 2.2f, 3.3f, 4.4f, 5.5f };
		Integer[] intsts0 = { 1, 7, 3, 4, 5 };

		Float[] mzs1 = { 1.2f, 2.3f, 3.4f, 4.5f, 5.6f, 6.5f, 6.7f };
		Integer[] intsts1 = { 6, 2, 3, 4, 5, 7, 6 };

		Float[] mzs2 = {  0.2f, 2.5f, 3.4f };
		Integer[] intsts2 = { 2, 3, 1 };

		Float[] mzs3 = {  0.2f, 2.1f, 3.6f };
		Integer[] intsts3 = { 2, 6, 4 };
		
		Float[] mzs4 = {  0.2f, 2.5f, 3.4f, 3.5f, 3.8f };
		Integer[] intsts4 = { 2, 3, 1, 3, 7 };
		
		Float[] mts = { 0.1f, 0.2f, 0.3f, 0.5f, 0.9f };
			
		
		Float example_mtime = 0.0f;
		Float example_mz = 0.0f;
		Integer example_intst = 0;
		Integer example_nummts = 0;
		Integer example_msdat_bsize = 0;
		Integer example_offspos = 0;
		
		RS_MassSpectra_simple1_6<Float, Float, Integer, Integer, Integer, Integer> multi_ms
			= new RS_MassSpectra_simple1_6<Float, Float, Integer, Integer, Integer, Integer>(
					example_mtime, example_mz, example_intst,
					example_nummts, example_msdat_bsize, example_offspos);
		
		multi_ms.add_ms(mts[ 0 ], mzs0, intsts0);
		multi_ms.add_ms(mts[ 1 ], mzs1, intsts1);		
		multi_ms.add_ms(mts[ 2 ], mzs2, intsts2);
		multi_ms.add_ms(mts[ 3 ], mzs3, intsts3);
		multi_ms.add_ms(mts[ 4 ], mzs4, intsts4);
		
		Path tmpfile = 
				Paths.get(System.getenv("RS_TMP_DIR")).
					resolve("rs_MSpectra").resolve("test_Java12.rsmspra");
				
		RSMassSpectra_simpleRW1_1<Float, Float, Integer,
			Integer, Integer, Integer>
			multi_ms_w = new RSMassSpectra_simpleRW1_1<Float, Float, Integer,
					Integer, Integer, Integer>(multi_ms, 256);
		
		
		
		/*
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
			
		
		Float example_mtime = 0.0f;
		Float example_mz = 0.0f;
		Long example_intst = 0L;
		Long example_nummts = 0L;
		Long example_msdat_bsize = 0L;
		Long example_offspos = 0L;
		
		RS_MassSpectra_simple1_6<Float, Float, Long, Long, Long, Long> multi_ms
			= new RS_MassSpectra_simple1_6<Float, Float, Long, Long, Long, Long>(
					example_mtime, example_mz, example_intst,
					example_nummts, example_msdat_bsize, example_offspos);
		
		multi_ms.add_ms(mts[ 0 ], mzs0, intsts0);
		multi_ms.add_ms(mts[ 1 ], mzs1, intsts1);		
		multi_ms.add_ms(mts[ 2 ], mzs2, intsts2);
		multi_ms.add_ms(mts[ 3 ], mzs3, intsts3);
		multi_ms.add_ms(mts[ 4 ], mzs4, intsts4);
		
		Path tmpfile = 
				Paths.get(System.getenv("RS_TMP_DIR")).
					resolve("rs_MSpectra").resolve("test_Java11.rsmspra");
				
		RSMassSpectra_simpleRW1_1<Float, Float, Long,
			Long, Long, Long>
			multi_ms_w = new RSMassSpectra_simpleRW1_1<Float, Float, Long,
					Long, Long, Long>(multi_ms, 256);
		*/
		
		
		multi_ms_w.output_to_file(tmpfile);	

		System.out.println(tmpfile);
		
	}

}
