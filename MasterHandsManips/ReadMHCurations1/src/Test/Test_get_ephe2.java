
package Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import emon2.EmonException;
import emon2.api.MasterHands;
import emon2.api.SessionInfo;

import RS.MasterHands.Ephe_to_MSpectra1_4;

public class Test_get_ephe2 {

	public static void main(String[] args) throws EmonException, IOException {
		// TODO Auto-generated method stub

		
		Path tmpmhfile = 
				Paths.get(System.getenv("RS_PROJ_DIR")).
					resolve("Quant_linear_check_STD").
					resolve("180426_TOF21_checkout").
					resolve("180426_C_TOF21.mhs");

		System.out.println(MasterHands.getVersionNumber());
		System.out.println(tmpmhfile);


		MasterHands mh = new MasterHands();
		
		mh.loadProject(tmpmhfile.toString());
		
		ArrayList<SessionInfo>   slist = mh.getSessionList();
		
		HashMap<Float, HashMap<Float, Integer>> mt_mz_to_intsty
			= new HashMap<Float, HashMap<Float, Integer>>();
		
		for(SessionInfo sess : slist) {
			
			System.out.println(sess.getName());
			
			Ephe_to_MSpectra1_4 ephe_to_mss = new Ephe_to_MSpectra1_4(mh, null, sess);
			
			LocalDateTime date1 = LocalDateTime.now();
			DateTimeFormatter dtformat = 
					DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
			String fdate1 = dtformat.format(date1);
			System.out.println(fdate1);
			
		}
		
		mh.closeProject();
		
		
	}

}
