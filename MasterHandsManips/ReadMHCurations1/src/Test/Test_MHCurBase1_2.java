
package Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import emon2.EmonException;

import RS.MasterHands.MHCurBase1_5;

public class Test_MHCurBase1_2 {
		
	public static void main(String[] args) throws EmonException, IOException {
		// TODO Auto-generated method stub

		System.out.println(System.getenv("RS_TMP_DIR"));
		
		/*
		Path tmpmhfile = 
				Paths.get(System.getenv("RS_PROJ_DIR")).
					resolve("Quant_linear_check_STD").
					resolve("180426_TOF21_checkout").
					resolve("180426_C_TOF21.mhs");

		Path tmpmhfile = 
				Paths.get("D:\\Data\\Metabolome\\Test\\J06_0101_plasma\\101_Plasma.mhs");

					*/

		Path tmpmhfile = 
				Paths.get(System.getenv("RS_PROJ_DIR")).
				resolve("MasterHands").
				resolve("Examples").
				resolve("samplefiles1").
				resolve("J06_0101_plasma").
				resolve("101_Plasma.mhs");
		
		/*
		Path tmpoutfolder =
				Paths.get(System.getenv("RS_TMP_DIR")).
					resolve("rs_MSpectra");
		 */
		
		Path tmpoutfolder =
				Paths.get(System.getenv("RS_TMP_DIR")).
					resolve("rs_MSpectra").resolve("J06_0101_plasma_MHout");
		
		MHCurBase1_5 mhcurbase = new MHCurBase1_5(tmpmhfile.toString());
		mhcurbase.output_peak_info(tmpoutfolder);
		mhcurbase.output_annot_info(tmpoutfolder);
		mhcurbase.output_spectra_unaligned(tmpoutfolder);
		mhcurbase.output_spectra_aligned(tmpoutfolder);
		
		mhcurbase.mh.closeProject();
		
		
	}

}
