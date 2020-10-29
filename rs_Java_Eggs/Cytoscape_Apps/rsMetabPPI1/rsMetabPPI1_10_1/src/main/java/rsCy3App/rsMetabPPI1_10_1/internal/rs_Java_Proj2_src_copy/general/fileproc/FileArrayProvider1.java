package rsCy3App.rsMetabPPI1_10_1.internal.rs_Java_Proj2_src_copy.general.fileproc;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// http://stackoverflow.com/questions/285712/java-reading-a-file-into-an-array
public class FileArrayProvider1 {

	private String filename;
	
	public FileArrayProvider1(String filename){
		this.filename = filename;
	}
		
	public String[] readLines() throws IOException {
		return readLines("");
	}
	
    public String[] readLines(String stripmode) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line_raw = null;
        while ((line_raw = bufferedReader.readLine()) != null) {
        	String line;
        	if(stripmode.equals("strip")){
        		line = line_raw.trim();
        	} else {
        		line = line_raw;
        	}
        	if(stripmode.equals("") || !line.equals("")){
        		lines.add(line);
        	}
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }
	
    public static void main(String args[]){
    	
        FileArrayProvider1 fap = new FileArrayProvider1("/Users/rsaito/Desktop/tmp1.txt");
        try {
        	String[] lines = fap.readLines("strip");
            for (String line : lines) {
                System.out.println(":" + line + ":");
            }
        } catch(IOException ioe){
        	System.err.println("File read error.");
        }
        
    }
    
}
