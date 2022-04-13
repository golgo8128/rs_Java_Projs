package OldVersions;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import emon2.EmonException;
import emon2.api.MasterHands;
import emon2.api.PeakInfo;
import emon2.api.SessionInfo;
import emon2.api.AlignmentInfo;
import emon2.api.ElectropherogramInfo;

public class Test_get_ephe1 {

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
		
		ArrayList<AlignmentInfo> alist = mh.getAlignmentList();
		ArrayList<SessionInfo>   slist = mh.getSessionList();
		
		HashMap<Float, HashMap<Float, Integer>> mt_mz_to_intsty
			= new HashMap<Float, HashMap<Float, Integer>>();
		
		for(SessionInfo sess : slist) {
			
			System.out.println(sess.getName());
			
			for(PeakInfo pk : sess.getPeakGroupList()) {
			
				ElectropherogramInfo ephe =
						mh.getElectropherogram(sess.getId(), pk.getId());
				
				float mz = ephe.getMz();
				List<Float>   mts    = ephe.getMtList();
				List<Integer> intsts = ephe.getIntensityList();
				
				for(Float mt: mts) {
					mt_mz_to_intsty.putIfAbsent(mt, new HashMap<Float, Integer>());
					for(Integer intsty : intsts) {
						mt_mz_to_intsty.get(mt).put(mz, intsty);
					}
				}
				
				
			}
			
			LocalDateTime date1 = LocalDateTime.now();
			DateTimeFormatter dtformat = 
					DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
			String fdate1 = dtformat.format(date1);
			System.out.println(fdate1);
			
		}
		
		
		/*
		for(AlignmentInfo each_a : alist) {
			System.out.println(each_a.getName());		
		}
		*/
		
		mh.closeProject();
		
		
	}

}
