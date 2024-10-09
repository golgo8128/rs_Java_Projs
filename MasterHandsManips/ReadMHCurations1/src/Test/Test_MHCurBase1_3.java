
package Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

import emon2.EmonException;

import RS.MasterHands.MHCurBase1_5;

public class Test_MHCurBase1_3 {
		
	public static void main(String[] args) throws EmonException, IOException {
		// TODO Auto-generated method stub

		// System.out.println(System.getenv("RS_TMP_DIR"));
	
		Scanner scanner_user_input = new Scanner(System.in);
		
		Path input_mhfile;
		
		if(args.length > 0) {
						
			input_mhfile = Paths.get(args[0].replaceAll("^['\"]", "").replaceAll("['\"]$", ""));
			
		} else {

			System.out.print("Input full path of input mhs file : ");
			input_mhfile = Paths.get(scanner_user_input.nextLine().replaceAll("^['\"]", "").replaceAll("['\"]$", ""));	
			
			/*
			Path input_mhfile = 
					Paths.get(System.getenv("RS_PROJ_DIR")).
					resolve("MasterHands").
					resolve("Examples").
					resolve("samplefiles1").
					resolve("J06_0101_plasma").
					resolve("101_Plasma.mhs");
			*/
	
		}
		
		Path output_folder;
		
		if(args.length > 1) {
			
			output_folder = Paths.get(args[1].replaceAll("^['\"]", "").replaceAll("['\"]$", ""));
			
		} else {
		
			System.out.print("Input full path of output folder  : ");
			output_folder = Paths.get(scanner_user_input.nextLine().replaceAll("^['\"]", "").replaceAll("['\"]$", ""));	
			
			/*
			Path output_folder =
					Paths.get(System.getenv("RS_TMP_DIR")).
						resolve("rs_MSpectra").resolve("MHands_out_J06_0101_plasma");
			*/
		
		}
		
		System.out.println("Input mhs file : " + input_mhfile);
		System.out.println("Output folder  : " + output_folder);		
		
		MHCurBase1_5 mhcurbase = new MHCurBase1_5(input_mhfile.toString());
		mhcurbase.output_peak_info(output_folder);
		mhcurbase.output_annot_info(output_folder);
		mhcurbase.output_spectra_unaligned(output_folder);
		mhcurbase.output_spectra_aligned(output_folder);
		
		mhcurbase.mh.closeProject();
		
		scanner_user_input.close();
		
	}

}
