package general.datastruct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class RSTable1 {

	private String[] rowlabels = null;
	private String[] collabels = null;
	
	private int nrows = 0;
	private int ncols = 0;
	
	private Class[] coldattypes = null;
	
	private HashMap rowlabel_to_idx_h = null;
	private HashMap collabel_to_idx_h = null;
	
	private Object[][] table = null;
	
	public RSTable1(){
	
		rowlabel_to_idx_h = new HashMap();
		collabel_to_idx_h = new HashMap();
		
	}
	
	public void read_table(String filename, String sep_regex, boolean rowlabels_flag, boolean collabels_flag){
	
		String[][] tbl2Dstr = readtablefile_simple1(filename, sep_regex);
		
		ncols = 0;
		nrows = 0;
		
		for(String[] each_row: tbl2Dstr){
			if(each_row.length > ncols){
				ncols = each_row.length;
			}
			nrows ++;
		}
		
		// System.out.println(nrows + " x " + ncols);
	
		table = new Object[nrows][ncols];
		
		int i = 0;
		for(String[] each_row: tbl2Dstr){
			int j = 0;
			for(String each_col: each_row){
				table[i][j] = each_col;
				j ++;
			}
			i ++;
		}		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RSTable1 rstable_test1 = new RSTable1();
		
		String testtable_file = rstable_test1.maketesttablefile();
		
		rstable_test1.read_table(testtable_file, "\\t", true, true);
	
		for(Object[] each_row: rstable_test1.table){
			for(Object each_elem: each_row){
				System.out.println(each_elem);
			}
		}

		RSTable1.get_colval_types(rstable_test1.table[0]);
		
	}

	public String [][] readtablefile_simple1(String filename, String sep){ // char, String[][]
		

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
	
	public String maketesttablefile(){
			
		File tmpFile = null;
		
		try{

			tmpFile = File.createTempFile("tmp", ".txt"); 
			System.out.println("Temp file : " + tmpFile.getAbsolutePath());

			BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
			
			String[][] teststr1 = testtable1();
			for(String[] strarray: teststr1){
				bw.write(StringUtils.join(strarray, "\t"));
				bw.newLine();			
			}
			
			bw.close();	
					
		} catch(IOException e){
			e.printStackTrace();

		}
		
		return tmpFile.getAbsolutePath();
		
	}
	
	
	public String[][] testtable1(){
		
		String[][] teststr1 = {
				{"",     "Col1", "Col2 (Integer)", "Col3"},
				{"Row1", "XXX1", "YYY2", "ZZZ3"},
				{"Row2", "XxX1", "YyY2"        },
				{"Row3", "XxX1", "YyY2", "ZzZ4"},
				{"Row4", "XxXx", "YyY1", "1111"},
		};
		
		return teststr1;
	}
	
	public static HashMap<String, Object> get_colval_types(Object[] icollabels_obj){
		// Object is Class.
		
		String[] icollabels = new String[icollabels_obj.length];
		for(int i = 0;i < icollabels_obj.length;i ++){
			icollabels[i] = (String)icollabels_obj[i];
		}
		
		HashMap<String, Object> collabels_to_type_h = new HashMap<String, Object>();
		
		String regex = "^(.*\\S)\\s*\\((\\S+)\\)$";
		Pattern pat  = Pattern.compile(regex);
		
		for(String each_str: icollabels){
			Matcher m = pat.matcher(each_str);
			String icollabel;
			String classtype;
			if(m.find()){
				icollabel = m.group(1);
				classtype = m.group(2);
			} else {
				icollabel = each_str;
				classtype = "String";
			}
			
			System.out.println(icollabel + " - " + classtype);
			
		}
		
		return collabels_to_type_h;
	}
}
