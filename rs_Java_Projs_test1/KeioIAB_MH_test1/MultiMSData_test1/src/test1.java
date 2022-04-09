import java.nio.file.Path;
import java.nio.file.Paths;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		float[] mz0 = { 1.1f, 2.2f, 3.3f, 4.4f, 5.5f };
		int[] intsty0 = { 1, 7, 3, 4, 5 };

		float[] mz1 = { 1.2f, 2.3f, 3.4f, 4.5f, 5.6f, 6.5f, 6.7f };
		int[] intsty1 = { 6, 2, 3, 4, 5, 7, 6 };

		float[] mz2 = {  0.2f, 2.5f, 3.4f };
		int[] intsty2 = { 2, 3, 1 };
		
		float[] mts = { 0.1f, 0.2f, 0.3f };
		
		MassSpec_simple1 ms0 = new MassSpec_simple1();
		MassSpec_simple1 ms1 = new MassSpec_simple1();
		MassSpec_simple1 ms2 = new MassSpec_simple1();
		
		ms0.mzs = mz0;
		ms0.intsties = intsty0;
				
		ms1.mzs = mz1;
		ms1.intsties = intsty1;

		ms2.mzs = mz2;
		ms2.intsties = intsty2;
		
		MultiMS_simple1 multi_ms = new MultiMS_simple1();
		
		multi_ms.mspecs = new MassSpec_simple1[3];
		multi_ms.mspecs[ 0 ] = ms0;
		multi_ms.mspecs[ 1 ] = ms1;		
		multi_ms.mspecs[ 2 ] = ms2;
		multi_ms.mtimes = mts;
		
		Path tmpfile = 
				Paths.get(System.getenv("RS_TMP_DIR")).
					resolve("rsMultiMSDat").resolve("test1.rsmmsd");
		
		System.out.println(tmpfile);
		
	}

}