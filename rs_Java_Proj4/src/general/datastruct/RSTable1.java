package general.datastruct;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RSTable1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{

			File tmpfile = File.createTempFile("tmp", ".txt"); 
			System.out.println("Temp file : " + tmpfile.getAbsolutePath());

			BufferedWriter bw = new BufferedWriter(new FileWriter(tmpfile));
			bw.write("Test...");
			bw.newLine();			
			
			bw.close();
			
		} catch(IOException e){
			e.printStackTrace();

		}

	}

}
