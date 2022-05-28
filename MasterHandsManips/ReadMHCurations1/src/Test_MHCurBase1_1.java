
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import emon2.EmonException;

import RS.MasterHands.MHCurBase1_2;

public class Test_MHCurBase1_1 {
		
	public static void main(String[] args) throws EmonException, IOException {
		// TODO Auto-generated method stub

		System.out.println(System.getenv("RS_TMP_DIR"));
		
		Path tmpmhfile = 
				Paths.get(System.getenv("RS_PROJ_DIR")).
					resolve("Quant_linear_check_STD").
					resolve("180426_TOF21_checkout").
					resolve("180426_C_TOF21.mhs");
		Path tmpoutfolder =
				Paths.get(System.getenv("RS_TMP_DIR")).
					resolve("rs_MSpectra");
		
		MHCurBase1_2 mhcurbase = new MHCurBase1_2(tmpmhfile.toString());
		mhcurbase.output_peak_info(tmpoutfolder);
		
		// mhcurbase.output_spectra_aligned(tmpoutfolder);
		
		mhcurbase.mh.closeProject();
		
		
	}

}
