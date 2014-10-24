package rsCy3App.rsMetabPPI1_12_2.internal.rs_Java_Proj4_cp.general.fileproc;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

public class NewFileAddedWatcher1_2 {

	private final String[] filnam_ends; // ex. *_info.txt, *_nodes.tsv (First check), *_edges.tsv ; make *_info.txt at the end.
	private final File check_folder;
	private HashMap<File, Long>last_poll_time;
	
	
	public NewFileAddedWatcher1_2(File check_folder, String[] filnam_ends){
	
		this.check_folder   = check_folder;
		this.filnam_ends    = filnam_ends;
		this.last_poll_time = new HashMap<File, Long>(); // Primary file only.
				
		get_new_file_paths_wo_ends();
		
	}
	
	
	public HashSet<String> get_new_file_paths_wo_ends(){
		// Returns valid file paths without ends specified by this.filnam_ends[1]

		HashSet<String>valid_headmids_path = new HashSet<String>();

		if(check_folder.exists()){
		
			HashSet<File> prim_files = new HashSet<File>();
			String prim_end_pat = filnam_ends[0];
			
			for(File ifile: check_folder.listFiles())			
				if(ifile.getAbsolutePath().endsWith(prim_end_pat) && 
						(!last_poll_time.containsKey(ifile) || ifile.lastModified() > last_poll_time.get(ifile)))
					prim_files.add(ifile); // Only last modification time for primary file is checked.

				
			for(File prim_file: prim_files){
				String target_filpath_headmid
					= prim_file.getAbsolutePath().substring(0, prim_file.getAbsolutePath().length() - prim_end_pat.length());
				boolean all_necessary_files_exist = true;
				for(int i = 1;i < filnam_ends.length;i ++){ // i = 0 is used for primary file name.
					File check_file = new File(target_filpath_headmid + filnam_ends[i]);
					if(!check_file.exists() || !check_file.isFile()){
						all_necessary_files_exist = false;
						break;
					}
				}
				if(all_necessary_files_exist){
					valid_headmids_path.add(target_filpath_headmid);
					last_poll_time.put(prim_file, prim_file.lastModified());
				}
				
			}
		}
			
		return valid_headmids_path;
		
	}
	
	public static void main(String[] args) {

		String[] filnam_ends = {"_info.txt", "_nodes.tsv", "_edges.tsv" };
		
		NewFileAddedWatcher1_2 nfaw = new NewFileAddedWatcher1_2(new File("E:\\WinAppl2\\cygwin\\home\\rsaito\\TMP"),
				filnam_ends);

		for(int i = 0;i < 900;i ++){
			HashSet<String> new_path_wo_ends = nfaw.get_new_file_paths_wo_ends();

			System.out.println(String.format("Trial #%d",i));
			for(String ipath_wo_end: new_path_wo_ends){
				System.out.println(ipath_wo_end);
			}
			
			try{
				Thread.sleep(3000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			
		}
		
	}

}
