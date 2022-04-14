
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

public class MHCurBase1_2 {

	public String mhfile;
	public MasterHands mh;
	private HashMap<Integer, HashMap<Integer, PeakInfo>> session_id_peak_id_to_peak_h;
	private HashMap<String, HashMap<String, HashMap<String, AlnPeakInfo_simple1>>> align_sess_pkgrpnam_h;
	
	final String SPECTRA_FILE_SUFFIX = "_centroided1_6.rsmspra";
	
	MHCurBase1_2(String imhfile)
			throws EmonException, IOException {

		System.out.println("##### MasterHands " + MasterHands.getVersionNumber() + " #####");
		
		this.mhfile = imhfile;
		this.mh     = new MasterHands();
		this.mh.loadProject(imhfile);
		
		this.load_info();
		
		// this.mh.closeProject(); Do not close the project until required data are output.
	}
	
	public void output_test() {
		
		for(String alignam: this.align_sess_pkgrpnam_h.keySet()) {
			for(String sesnam: this.align_sess_pkgrpnam_h.get(alignam).keySet()) {
				for(String annot: this.align_sess_pkgrpnam_h.get(alignam).get(sesnam).keySet()) {
					
					AlnPeakInfo_simple1 alnpkinfo
						= this.align_sess_pkgrpnam_h.get(alignam).get(sesnam).get(annot);
					
					System.out.println(
						String.join("\t",
								alignam, sesnam, annot,
								String.valueOf(alnpkinfo.epeak_before_align.getMt()),
								String.valueOf(alnpkinfo.epeak.getMt())
								)
							);
				
				}
			}

		}

	}
	
	
	public void output_spectra_unaligned(Path output_folder) throws IOException {
		
		HashMap<String, RS_MassSpectra_simple1_2<Float, Float, Integer>>
			session_name_to_RS_MSS_h = this.get_spectra_unaligned();
		
		for(String sessnam : session_name_to_RS_MSS_h.keySet()) {
			
			Path basename = Paths.get(sessnam + SPECTRA_FILE_SUFFIX);
			Path ofile = output_folder.resolve(basename);
			session_name_to_RS_MSS_h.get(sessnam).output_to_file(ofile, 256);
			
		}
		
		
	}

	public HashMap<String, RS_MassSpectra_simple1_2<Float, Float, Integer>>
		get_spectra_unaligned(){
		
		HashMap<String, RS_MassSpectra_simple1_2<Float, Float, Integer>>
			session_name_to_RS_MSS_h
				= new HashMap<String, RS_MassSpectra_simple1_2<Float, Float, Integer>>();
		
		for(SessionInfo sess : this.mh.getSessionList()) {
			Ephe_to_MSpectra1 ephe_mss = new Ephe_to_MSpectra1(this.mh, sess);
			session_name_to_RS_MSS_h.put(sess.getName(), ephe_mss.to_RS_MSS());
			
			System.out.println(ClockSimple1.current_time("yyyy/MM/dd HH:mm:ss.SSS")
					+ " Generated spectra from " + sess.getName());
			
		}
		
		return(session_name_to_RS_MSS_h);
		
	}
	
	private void load_info(){
		
		this._set_session_id_peak_id_to_peak_h();
		
		ArrayList<AlignmentInfo> alist = mh.getAlignmentList();
		
		this.align_sess_pkgrpnam_h
			= new HashMap<String, HashMap<String, HashMap<String, AlnPeakInfo_simple1>>>();
		
		for(AlignmentInfo calign : alist) {
			
			this.align_sess_pkgrpnam_h.putIfAbsent(
					calign.getName(), new HashMap<String, HashMap<String, AlnPeakInfo_simple1>>());
			
			List<SessionInfo> session_list = calign.getSessionList();
			
			for(PeakInfo caln_peak_grp : calign.getPeakGroupList()) {
				
				List<PeakInfo> peaknode_list = caln_peak_grp.getPeakNodeList();
				
				for(int i = 0; i < calign.getSessionList().size(); i ++) {
					SessionInfo csession = session_list.get(i);
					PeakInfo caln_peak = peaknode_list.get(i);

					if(caln_peak == null) { continue; } // M.P ... check
					
					this.align_sess_pkgrpnam_h.get(calign.getName())
						.putIfAbsent(csession.getName(), new HashMap<String, AlnPeakInfo_simple1>());
					
					HashMap<String, AlnPeakInfo_simple1> alnpkgrpannot_to_pkinfo_h
						= this.align_sess_pkgrpnam_h.get(calign.getName()).get(csession.getName());
					
					if(!alnpkgrpannot_to_pkinfo_h.containsKey(caln_peak_grp.getAnnotation())) {
						
						AlnPeakInfo_simple1 align_sess
							= new AlnPeakInfo_simple1(calign, csession, caln_peak_grp, caln_peak);
						PeakInfo peak_before_align =
							this.session_id_peak_id_to_peak_h
								.get(csession.getId()).get(caln_peak.getId());
						align_sess.set_epeak_before_align(peak_before_align);
						
						alnpkgrpannot_to_pkinfo_h.put(caln_peak_grp.getAnnotation(), align_sess);
						
						/*
						System.out.println(calign.getName() +
								" - " + csession.getName() +
								" : " + caln_peak_grp.getAnnotation());
							*/							
					}
                    
				}
				
			}
				
		}
			
	}
		
	
	private void _set_session_id_peak_id_to_peak_h() {
		
		this.session_id_peak_id_to_peak_h =
				new HashMap<Integer, HashMap<Integer, PeakInfo>>();
		
		ArrayList<SessionInfo> slist = this.mh.getSessionList();
		for(SessionInfo sess : slist) {
			System.out.println("Getting " +
								sess.getPeakGroupList().size() +
								" peak IDs and their corresponding peak objects from " + sess.getName());
	        for(PeakInfo cpeak : sess.getPeakGroupList()) {
	        	this.session_id_peak_id_to_peak_h
	        		.putIfAbsent(sess.getId(),
	        					 new HashMap<Integer, PeakInfo>());
	        	this.session_id_peak_id_to_peak_h
	        		.get(sess.getId()).put(cpeak.getId(), cpeak);
	        }
		}
		
	}
	
	
}
