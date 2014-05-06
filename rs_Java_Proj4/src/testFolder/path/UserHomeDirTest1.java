package testFolder.path;

import general.strproc.StrUtil1;

import java.io.File;

public class UserHomeDirTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] tpn_path_split = {".rs_Progs_data", "Cytoscape", "TPN"};
		File path = new File(System.getProperty("user.home"), (StrUtil1.strjoin(tpn_path_split, File.separator)));
		
		System.out.println(path);
		
	}

}
