
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import emon2.EmonException;


public class MHCurBase1_test1 {
		
	public static void main(String[] args) throws EmonException, IOException {
		// TODO Auto-generated method stub

		
		Path tmpmhfile = 
				Paths.get(System.getenv("RS_PROJ_DIR")).
					resolve("Quant_linear_check_STD").
					resolve("180426_TOF21_checkout").
					resolve("180426_C_TOF21.mhs");

		MHCurBase1_2 mhcurbase = new MHCurBase1_2(tmpmhfile.toString());
		mhcurbase.mh.closeProject();
		
		
	}

}
