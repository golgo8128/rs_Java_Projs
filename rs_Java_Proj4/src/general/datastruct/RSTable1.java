package general.datastruct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;


public class RSTable1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RSTable1 rstable_test1 = new RSTable1();
		
		File tmpFile = null;
		
		try{

			tmpFile = File.createTempFile("tmp", ".txt"); 
			System.out.println("Temp file : " + tmpFile.getAbsolutePath());

			BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
			
			String[][] teststr1 = rstable_test1.testtable1();
			for(String[] strarray: teststr1){
				bw.write(StringUtils.join(strarray, "\t"));
				bw.newLine();			
			}
			
			bw.close();	
					
		} catch(IOException e){
			e.printStackTrace();

		}

		String[][] tmptbl = rstable_test1.readtable_simple1(tmpFile.getAbsolutePath(), "\\t");
		
		for(String[] each_line:tmptbl){
			for(String each_cell: each_line){
				System.out.println("!" + each_cell);	
			}
			System.out.println("----");
		}
		
		
	}

	public String [][] readtable_simple1(String filename, String sep){ // char, String[][]
		

		ArrayList<String[]> lines = new ArrayList<String[]>();
		
		try {
			String sCurrentLine;
 
			BufferedReader br = new BufferedReader(new FileReader(filename));
 
			while ((sCurrentLine = br.readLine()) != null) {
				// Doing rstrip unnecessary?
				String[] r = sCurrentLine.split(sep);
//				for(String estr: r){
//					System.out.println("!" + estr);					
//				}
				lines.add(r);
			}
			
			br.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return (String[][])lines.toArray(new String[0][0]);
		
	}
	
	
	public String[][] testtable1(){
		
		String[][] teststr1 = {
				{"",     "Col1", "Col2", "Col3"},
				{"Row1", "XXX1", "YYY2", "ZZZ3"},
				{"Row2", "XxX1", "YyY2", "ZzZ4"},
				{"Row3", "XxX1", "YyY2", "ZzZ4"},
				{"Row4", "XxXx", "YyY1", "1111"},
		};
		
		return teststr1;
	}
	
	
}
