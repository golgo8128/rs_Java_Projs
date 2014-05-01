package general.datastruct;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;


public class RSTable1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RSTable1 rstable_test1 = new RSTable1();
		
		try{

			File tmpfile = File.createTempFile("tmp", ".txt"); 
			System.out.println("Temp file : " + tmpfile.getAbsolutePath());

			BufferedWriter bw = new BufferedWriter(new FileWriter(tmpfile));
			
			String[][] teststr1 = rstable_test1.testtable1();
			for(String[] strarray: teststr1){
				bw.write(StringUtils.join(strarray, "; "));
				bw.newLine();			
			}
			
			bw.close();	
		} catch(IOException e){
			e.printStackTrace();

		}

	}

	public String[][] readtable_simple1(String filename, char sep){
		
		
		
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
