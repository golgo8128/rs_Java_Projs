
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import emon2.EmonException;
import emon2.api.MasterHands;
import emon2.api.PeakInfo;
import emon2.api.SessionInfo;
import emon2.api.AlignmentInfo;
import emon2.api.ElectropherogramInfo;

public class MHCurBase1 {

	public String mhfile;
	public MasterHands mh;
	private HashMap<Integer, HashMap<Integer, PeakInfo>> session_id_peak_id_to_peak_h;
	private HashMap<String, HashMap<String, HashMap<String, AlignSession1>>> align_sess_pkgrpnam_h;
	private HashMap<String, HashMap<String, PeakGrp_simple1>> align_pkgrpnam_h;
	
	
	MHCurBase1(String imhfile)
			throws EmonException, IOException {

		System.out.println("##### MasterHands " + MasterHands.getVersionNumber() + " #####");
		
		this.mhfile = imhfile;
		this.mh     = new MasterHands();
		this.mh.loadProject(imhfile);
		
		this.get_info();
		
		// this.mh.closeProject();
	}
	

	private void get_info(){
		
		this._set_session_id_peak_id_to_peak_h();
		
		ArrayList<AlignmentInfo> alist = mh.getAlignmentList();
		
		this.align_sess_pkgrpnam_h
			= new HashMap<String, HashMap<String, HashMap<String, AlignSession1>>>();
		this.align_pkgrpnam_h
			= new HashMap<String, HashMap<String, PeakGrp_simple1>>();
		
		for(AlignmentInfo calign : alist) {
			
			this.align_sess_pkgrpnam_h.putIfAbsent(
					calign.getName(), new HashMap<String, HashMap<String, AlignSession1>>());
			this.align_pkgrpnam_h.putIfAbsent(
					calign.getName(), new HashMap<String, PeakGrp_simple1>());
			
			List<SessionInfo> session_list = calign.getSessionList();
			
			for(PeakInfo caln_peak_grp : calign.getPeakGroupList()) {
					
				// this.align_pkgrpnam_h.get(
				//		calign.getName()).put(caln_peak_grp.getAnnotation(), )	
				
				List<PeakInfo> peaknode_list = caln_peak_grp.getPeakNodeList();
				
				for(int i = 0; i < calign.getSessionList().size(); i ++) {
					SessionInfo csession = session_list.get(i);
					PeakInfo caln_peak = peaknode_list.get(i);
					System.out.println(caln_peak);
					if(caln_peak == null) { continue; } // M.P ... check
					
					this.align_sess_pkgrpnam_h.get(calign.getName())
						.putIfAbsent(csession.getName(), new HashMap<String, AlignSession1>());
					
					if(!this.align_sess_pkgrpnam_h.get(calign.getName())
						.get(csession.getName()).containsKey(caln_peak_grp.getAnnotation())) {
						
						AlignSession1 align_sess = new AlignSession1();
						
						if(!this.align_pkgrpnam_h.get(calign.getName())
							.containsKey(caln_peak_grp.getAnnotation())) {
							
							PeakGrp_simple1 pkgrp_simp = new PeakGrp_simple1();
							
							
						}
						
						
						
						// print("Generating electropherogram ...", csession.getName(), caln_peak.getAnnotation())
	                    ElectropherogramInfo chrom
	                    	= this.mh.getElectropherogram(csession.getId(), caln_peak.getId());
	                    // print("Generating electropherogram after alignment ...")
	                    ElectropherogramInfo aln_chrom
	                    	= this.mh.getElectropherogram(calign.getId(), csession.getId(), caln_peak.getId());
	                    // print("Generating electropherogram done.")
						
						
						
						
					}

					
					

					
                    
                    
				}
				

				
			}
			
			
		}
		
		
	}

	private void _set_session_id_peak_id_to_peak_h() {
		
		this.session_id_peak_id_to_peak_h =
				new HashMap<Integer, HashMap<Integer, PeakInfo>>();
		
		ArrayList<SessionInfo> slist = mh.getSessionList();
		for(SessionInfo sess : slist) {
			System.out.println("Getting peak IDs and their corresponding peak objects from " + sess.getName());
	        for(PeakInfo cpeak : sess.getPeakGroupList()) {
	        	this.session_id_peak_id_to_peak_h
	        		.putIfAbsent(sess.getId(),
	        					 new HashMap<Integer, PeakInfo>());
	        	this.session_id_peak_id_to_peak_h
	        		.get(sess.getId()).put(cpeak.getId(), cpeak);
	        }
		}
		
	}
	
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
		
		// ArrayList<AlignmentInfo> alist = mh.getAlignmentList();
		ArrayList<SessionInfo>   slist = mh.getSessionList();
		


		
		for(SessionInfo sess : slist) {

			System.out.println(sess.getName());	
			Ephe_to_MSpectra1 ephe_to_mss = new Ephe_to_MSpectra1(mh, sess);
			ArrayList<Float> tmpmzs = ephe_to_mss.get_nonredu_mzs();
			System.out.println(tmpmzs.size());
		}
		
		
		/*
		for(AlignmentInfo each_a : alist) {
			System.out.println(each_a.getName());		
		}
		*/
		
		mh.closeProject();
		
		
	}

}
