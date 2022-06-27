package RS.MasterHands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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

import RS.MassSpec.*;
import RS.Usefuls1.*;

public class MHCurBase1_4 {

	public String mhfile;
	public MasterHands mh;
	private HashMap<Integer, HashMap<Integer, PeakInfo>> session_id_peak_id_to_peak_h;
	private HashMap<String, HashMap<String, HashMap<String, AlnPeakInfo_simple1>>> align_sess_pkgrpnam_h;
	
	final String PEAK_INFO_FILE_SUFFIX = "_peakinfo1_7.tsv";
	final String SPECTRA_FILE_MT_ADJUST_PREFIX = "align_";
	final String SPECTRA_FILE_SUFFIX   = "_centroided1_7.rsmspra";
	final String ANNOT_FILE_PREFIX = "annotinfo_MHout_";
	final int OFFSET_BYTE_SIZE = 256;
	
	public MHCurBase1_4(String imhfile)
			throws EmonException, IOException {

		System.out.println("##### MasterHands " + MasterHands.getVersionNumber() + " #####");
		
		this.mhfile = imhfile;
		this.mh     = new MasterHands();
		this.mh.loadProject(imhfile);
		
		this.load_info();
		
		// this.mh.closeProject(); Do not close the project until required data are output.
	}
	
	public void output_peak_info(Path output_folder) throws IOException {
		
		Files.createDirectories(output_folder);
		
		for(String alignam: this.align_sess_pkgrpnam_h.keySet()) {
			for(String sesnam: this.align_sess_pkgrpnam_h.get(alignam).keySet()) {
				
				Path basename = Paths.get(alignam + "_" + sesnam + PEAK_INFO_FILE_SUFFIX);
				Path ofile = output_folder.resolve(basename);
				FileWriter fw = new FileWriter(ofile.toFile());

				fw.write(String.join("\t",
						 "Peak annotation",
						 "Peak m/z",
						 "Peak MT start",
						 "Peak MT end",
						 "Peak MT top",
						 "Peak MT start after alignment",
						 "Peak MT end after alignment",
						 "Peak MT top after alignment") + '\n');
				
				for(String annot: this.align_sess_pkgrpnam_h.get(alignam).get(sesnam).keySet()) {
					
					AlnPeakInfo_simple1 alnpkinfo
						= this.align_sess_pkgrpnam_h.get(alignam).get(sesnam).get(annot);
					
					if(alnpkinfo.epeak_before_align.getMt() > 0) {
						fw.write(String.join("\t",
								 annot,
								 String.valueOf(alnpkinfo.epeak_before_align.getMz()),
								 String.valueOf(alnpkinfo.epeak_before_align.getLeftmt()),
								 String.valueOf(alnpkinfo.epeak_before_align.getRightmt()),
								 String.valueOf(alnpkinfo.epeak_before_align.getMt()),
	
								 String.valueOf(alnpkinfo.epeak.getLeftmt()),
								 String.valueOf(alnpkinfo.epeak.getRightmt()),
								 String.valueOf(alnpkinfo.epeak.getMt())) + '\n');
					} else {
						
						fw.write(String.join("\t",
									 annot,
									 String.valueOf(alnpkinfo.epeak_before_align.getMz()),
									 "",
									 "",
									 "",
		
									 "",
									 "",
								 	 "") + '\n');						
						
					}
					

				}
				
				fw.close();
				
			}

		}

	}
	
	
	public void output_spectra_unaligned(Path output_folder) throws IOException {
		
		Files.createDirectories(output_folder);
		
		HashMap<String, RS_MassSpectra_simple1_5<Float, Float, Integer>>
			session_name_to_RS_MSS_h = this.get_spectra_unaligned();
		
		for(String sessnam : session_name_to_RS_MSS_h.keySet()) {
			
			Path basename = Paths.get(sessnam + SPECTRA_FILE_SUFFIX);
			Path ofile = output_folder.resolve(basename);
			session_name_to_RS_MSS_h.get(sessnam).output_to_file(ofile, OFFSET_BYTE_SIZE);
			
		}
		
		
	}

	public void output_spectra_aligned(Path output_folder) throws IOException {
		
		Files.createDirectories(output_folder);
		
		HashMap<String, HashMap<String, RS_MassSpectra_simple1_5<Float, Float, Integer>>>
			align_session_name_to_RS_MSS_h = this.get_spectra_aligned();
		
		for(String alignnam : align_session_name_to_RS_MSS_h.keySet()) {
			for(String sessnam : align_session_name_to_RS_MSS_h.get(alignnam).keySet()) {
				
				Path basename = Paths.get(SPECTRA_FILE_MT_ADJUST_PREFIX
						+ alignnam + "_" + sessnam + SPECTRA_FILE_SUFFIX);
				Path ofile = output_folder.resolve(basename);
				align_session_name_to_RS_MSS_h.get(alignnam).get(sessnam)
					.output_to_file(ofile, OFFSET_BYTE_SIZE);
			
			}
		
		}
	}
	
	
	public void output_annot_info(Path output_folder) throws EmonException, IOException {
		
		Files.createDirectories(output_folder);
		
		List<AlignmentInfo> alist = mh.getAlignmentList();
		
		for(AlignmentInfo each_a : alist) {
			
			Path ofile = output_folder.resolve(ANNOT_FILE_PREFIX + each_a.getName() + ".csv");
			
			// System.out.println(each_a.getName());
			mh.exportCsv(
					each_a.getId(), // resultId
					ofile.toString(),
		            false, // printZeroValues
		            true,  // printGroup
		            true,  // printSession
		            true,  // printPeakGroupId
		            true,  // printAnnotationId
		            true,  // printAnnotationName
		            true,  // printAveMz
		            true,  // printAveCorMT
		            true,  // printN
		            true,  // printIs
		            true,  // printCategory
		            true,  // printTscore
		            true,  // printPvalue
		            true,  // printPeakId
		            true,  // printMz
		            true,  // printCorMT
		            true,  // printMT
		            true,  // printIntensity,
		            true,  // printArea
		            true,  // printRelArea
		            true,  // printSn
		            true,  // printNoise
		            true,  // printHeight
		            true,  // printLeftMT
		            true,  // printRightMT
		            true,  // printConc
		            true   // printTag
		            );
		}
		
	}
	
	
	public HashMap<String, RS_MassSpectra_simple1_5<Float, Float, Integer>>
		get_spectra_unaligned(){
		
		HashMap<String, RS_MassSpectra_simple1_5<Float, Float, Integer>>
			session_name_to_RS_MSS_h
				= new HashMap<String, RS_MassSpectra_simple1_5<Float, Float, Integer>>();
		
		for(SessionInfo sess : this.mh.getSessionList()) {
			Ephe_to_MSpectra1_3 ephe_mss = new Ephe_to_MSpectra1_3(this.mh, null, sess);
			session_name_to_RS_MSS_h.put(sess.getName(), ephe_mss.to_RS_MSS());
			
			System.out.println(ClockSimple1.current_time("yyyy/MM/dd HH:mm:ss.SSS")
					+ " Generated spectra from " + sess.getName());
			
		}
		
		return(session_name_to_RS_MSS_h);
		
	}
	
	
	public HashMap<String, HashMap<String, RS_MassSpectra_simple1_5<Float, Float, Integer>>>
		get_spectra_aligned(){
	
		HashMap<String, HashMap<String, RS_MassSpectra_simple1_5<Float, Float, Integer>>>
			align_session_name_to_RS_MSS_h
				= new HashMap<String, HashMap<String, RS_MassSpectra_simple1_5<Float, Float, Integer>>>();
		
		for(AlignmentInfo align : this.mh.getAlignmentList()) {
			
			align_session_name_to_RS_MSS_h.putIfAbsent(
					align.getName(),
					new HashMap<String, RS_MassSpectra_simple1_5<Float, Float, Integer>>());
			
			for(SessionInfo sess : align.getSessionList()) {
				Ephe_to_MSpectra1_3 ephe_mss = new Ephe_to_MSpectra1_3(this.mh, align, sess);
				align_session_name_to_RS_MSS_h.get(align.getName()).put(sess.getName(), ephe_mss.to_RS_MSS());
				
				System.out.println(ClockSimple1.current_time("yyyy/MM/dd HH:mm:ss.SSS")
						+ " Generated spectra with MT adjusted from " + 
						align.getName() + " " +	sess.getName());
				
			}
		}
	
		return(align_session_name_to_RS_MSS_h);
	
	}
	
	
	
	private void load_info(){
		
		this._set_session_id_peak_id_to_peak_h();
		
		ArrayList<AlignmentInfo> alist = mh.getAlignmentList();
		
		this.align_sess_pkgrpnam_h
			= new HashMap<String, HashMap<String, HashMap<String, AlnPeakInfo_simple1>>>();
		// alignment name -> session name -> peak group name : AlnPeakInfo_simple
		
		for(AlignmentInfo calign : alist) {
			
			this.align_sess_pkgrpnam_h.putIfAbsent(
					calign.getName(), new HashMap<String, HashMap<String, AlnPeakInfo_simple1>>());
			
			List<SessionInfo> session_list = calign.getSessionList();
			
			for(PeakInfo caln_peak_grp : calign.getPeakGroupList()) {
				
				List<PeakInfo> peaknode_list = caln_peak_grp.getPeakNodeList();
				// peaknode_list : peaks from list of sessions.
				// The peaks correspond to a single metabolite.
				
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
