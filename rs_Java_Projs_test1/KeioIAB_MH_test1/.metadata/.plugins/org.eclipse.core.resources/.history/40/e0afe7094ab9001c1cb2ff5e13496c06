
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test1_2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Float[] mzs0 = { 1.1f, 2.2f, 3.3f, 4.4f, 5.5f };
		Integer[] intsts0 = { 1, 7, 3, 4, 5 };

		Float[] mzs1 = { 1.2f, 2.3f, 3.4f, 4.5f, 5.6f, 6.5f, 6.7f };
		Integer[] intsts1 = { 6, 2, 3, 4, 5, 7, 6 };

		Float[] mzs2 = {  0.2f, 2.5f, 3.4f };
		Integer[] intsts2 = { 2, 3, 1 };
		
		Float[] mts = { 0.1f, 0.2f, 0.3f };
				
		MultiMS_simple1_2<Float, Float, Integer> multi_ms
			= new MultiMS_simple1_2<Float, Float, Integer>();
		
		multi_ms.add_ms(mts[ 0 ], mzs0, intsts0);
		multi_ms.add_ms(mts[ 1 ], mzs1, intsts1);		
		multi_ms.add_ms(mts[ 2 ], mzs2, intsts2);
		
		Path tmpfile = 
				Paths.get(System.getenv("RS_TMP_DIR")).
					resolve("rsMultiMSDat").resolve("test1.rsmmsd");
		multi_ms.output_to_file(tmpfile);
		
		int tmp11[] = multi_ms.sizes_intsts();
		System.out.println(tmpfile);
		
	}

}
