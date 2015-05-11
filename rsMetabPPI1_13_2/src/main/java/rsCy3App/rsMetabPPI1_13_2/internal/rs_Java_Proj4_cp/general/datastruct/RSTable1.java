package rsCy3App.rsMetabPPI1_13_2.internal.rs_Java_Proj4_cp.general.datastruct;

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

import rsCy3App.rsMetabPPI1_13_2.internal.rs_Java_Proj4_cp.general.strproc.StrUtil1;

// import org.apache.commons.lang3.StringUtils;


public class RSTable1 {

	private String[] rowlabels = null;
	private String[] collabels = null;
	
	private int nrows = 0;
	private int ncols = 0;
	
	private Class<?>[] coldatClasses = null;
	
	private HashMap<String, Integer> rowlabel_to_idx_h = null;
	private HashMap<String, Integer> collabel_to_idx_h = null;
	
	private Object[][] table = null;
	
	public RSTable1(){
			
	}
	
	public int get_nrows(){
		return nrows;
	}

	public int get_ncols(){
		return ncols;
	}
	
	public String[] get_rowlabels(){
		return rowlabels;
	}

	public String[] get_collabels(){
		return collabels;
	}
	
	public Class<?>[] get_coldatClasses(){
		return coldatClasses;
	}
	
	public Object get_elem_by_idx(int i, int j){
		// You can define Exception for index out of range error.
		return table[i][j];
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
	
		int start_i = 0;
		int start_j = 0;
		
		if(rowlabels_flag){
			start_j = 1;
			ncols --;
		}
		if(collabels_flag){
			start_i = 1;
			nrows --;
		}

		rowlabels = new String[nrows];
		rowlabel_to_idx_h = new HashMap<String, Integer>();	
		for(int i = 0;i < nrows;i ++){
			if(rowlabels_flag)
				rowlabels[i] = tbl2Dstr[i+start_i][0];
			else
				rowlabels[i] = String.format("Row:%d",i);
			rowlabel_to_idx_h.put(rowlabels[i], i);
		}
		
		
		collabels = new String[ncols];		
		collabel_to_idx_h = new HashMap<String, Integer>();
		for(int j = 0;j < ncols;j ++){
			if(collabels_flag)
				collabels[j] = tbl2Dstr[0][j+start_j];
			else
				collabels[j] = String.format("Col:%d",j);
			collabel_to_idx_h.put(collabels[j], j);
		}


		HashMap<String, Object[]> colval_types_h = RSTable1.get_colval_types(collabels);
		Object[] collabels_obj = colval_types_h.get("collabels");
		Object[] klasses_obj   = colval_types_h.get("Classes");		
		coldatClasses = new Class<?>[collabels.length];
		for(int j = 0;j < collabels_obj.length;j ++){
			collabels[j]     = (String)collabels_obj[j];
			coldatClasses[j] = (Class<?>)klasses_obj[j];
		}
					
		table = new Object[nrows][ncols];
		
		for(int i = 0;i < nrows;i ++){
			for(int j = 0;j < ncols;j++){
				if(j+start_j < tbl2Dstr[i+start_i].length)
					table[i][j] = tbl2Dstr[i+start_i][j+start_j];
				else
					table[i][j] = null;
			}
		}
		
		turn_colvaltypes();
		
	}
	
	public void turn_colvaltypes(){
		// All the attribute variables must be set before running this method.
		for(int j = 0;j < ncols;j ++){
			Class<?> colvaltype = coldatClasses[j];
			for(int i = 0;i < nrows;i ++){
				if(table[i][j] == null)continue;
				if(colvaltype.equals(Integer.class)){
					table[i][j] = Integer.parseInt((String)table[i][j]);
				}
				else if(colvaltype.equals(Float.class)){
					table[i][j] = Float.parseFloat((String)table[i][j]);	
				}
				else if(colvaltype.equals(Double.class)){
					table[i][j] = Double.parseDouble((String)table[i][j]);	
				}			
				else if(colvaltype.equals(Boolean.class)){
					table[i][j] = Boolean.parseBoolean((String)table[i][j]);	
				}
				else if(!colvaltype.equals(String.class)){
					throw new IllegalStateException(String.format("Incompatible class %s", colvaltype.toString()));
				}
				
			}
		}
		
	}
	
	public void print_info(){
		
		System.out.println("Row labels: " + StrUtil1.strjoin(rowlabels, "\t")); // StringUtils.join(rowlabels, "\t"));
		System.out.println("Col labels: " + StrUtil1.strjoin(collabels, "\t")); // StringUtils.join(collabels, "\t"));

		System.out.println("Table:");
		for(Object[] each_row: table){
			for(int j = 0;j < each_row.length;j ++){
				Object each_elem = each_row[j];
				if(each_elem == null){
					System.out.print("NULL");
				} else {
					System.out.print(each_elem.toString());
				}
				if(j < each_row.length - 1){
					System.out.print(" ; ");
				}
			}
			System.out.println();
		}
		
		System.out.println("Column value classes:");
		for(int j = 0;j < coldatClasses.length;j ++){
			System.out.println(collabels[j] + " -> " + coldatClasses[j].toString());
		}


		System.out.println("Row labels to indices:");
		for(String rowlabel: rowlabel_to_idx_h.keySet()){
			System.out.println(rowlabel + " ... " + rowlabel_to_idx_h.get(rowlabel));
		}

		System.out.println("Column labels to indices:");
		for(String collabel: collabel_to_idx_h.keySet()){
			System.out.println(collabel + " ... " + collabel_to_idx_h.get(collabel));
		}		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RSTable1 rstable_test1 = new RSTable1();
		
		String testtable_file = rstable_test1.maketesttablefile(1);
		
		System.out.println("Temp file : " + testtable_file);
		
		rstable_test1.read_table(testtable_file, "\\t", true, true);
		rstable_test1.print_info();
		
	}

	public String [][] readtablefile_simple1(String filename, String sep){ // char
		

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
	
	public String maketesttablefile(int testtbl_num){
			
		File tmpFile = null;
		
		try{

			tmpFile = File.createTempFile("tmp", ".txt"); 

			BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
			
			String[][] teststr1 = null;
			switch(testtbl_num){
				case 1: teststr1 = testtable1_1();break;
				case 2: teststr1 = testtable1_2();break;
				case 3: teststr1 = testtable1_3();break;
			}
			
			for(String[] strarray: teststr1){
				bw.write(StrUtil1.strjoin(strarray, "\t")); // StringUtils.join(strarray, "\t"));
				bw.newLine();			
			}
			
			bw.close();	
					
		} catch(IOException e){
			e.printStackTrace();

		}
		
		return tmpFile.getAbsolutePath();
		
	}
	
	
	public String[][] testtable1_1(){
		
		String[][] teststr1 = {
				{"",     "Col1 (X) (Boolean)", "Col2 (Integer)", "Col3 (XXX)", "Col4(Double)", "Col5(Float)"},
				{"Row1", "R1C1", "12", "R1C3", "1.2", "2.3"  },
				{"Row2", "R2C1", "22", "R2C3", ""   ,        },
				{"Row3", "true", "32", "R3C3", "2.1", "-1.2" },
				{"Row4", "R4C1", "42", "R4C3", "3.5" },
		};
		
		return teststr1;
	}
	
	
	public String[][] testtable1_2(){
		
		String[][] teststr1 = {
				{"Node ID", "Node symbol",  "Node type", "heat (Double)", "Score (Double)"},
				{"Node_A", "Node A", "Blue", "+1.2", "0.9" },
				{"Node_B", "Node B", "Blue", "-3.1", "0.7" },
				{"Node_C", "Node C", "Pink", "-3.2", "0.5" },
				{"Node_D", "Node D", "Pink", "+5.5", "0.3" },
				{"Node_E", "Node E", "Pink", "-1.2", "0.1" }
		};
		
		return teststr1;
	}	
	
	public String[][] testtable1_3(){
		
		String[][] teststr1 = {
				{"Node ID1",   "Node ID2", "interaction", "Categ", "heat (Double)"},
				{"Node_A", "Node_B", "Friend", "Blue",  "+1.0" },
				{"Node_A", "Node_C", "Friend", "Blue",  "+1.7" },
				{"Node_A", "Node_D", "Friend", "Blue",  "-1.2" },
				{"Node_B", "Node_D", "Enemy",  "Blue",  "+1.3" },
				{"Node_D", "Node_E", "Enemy",  "Green", "+1.4" },
				{"Node_E", "Node_B", "Enemy",  "Red",   "+1.6" }
		};
		
		return teststr1;
	}	
	
	
	public static HashMap<String, Object[]> get_colval_types(Object[] icollabels_obj){
		// String: "collabels" -> String collabels[]
		//         "Classes"   -> Class klasses[]
				
		String[] icollabels = new String[icollabels_obj.length];
		for(int i = 0;i < icollabels_obj.length;i ++){
			icollabels[i] = (String)icollabels_obj[i];
		}
			
		String regex = "^(.*\\S)\\s*\\(([^\\s\\)]+)\\)$";
		Pattern pat  = Pattern.compile(regex);
		
		String[] icollbs     = new String[icollabels_obj.length];
		Object[] icolClasses = new Object[icollabels_obj.length];
		
		for(int i = 0;i < icollabels_obj.length;i ++){
			String each_str = icollabels[i];
			Matcher m = pat.matcher(each_str);
			String icollabel;
			String classtype;
			Object classtype_obj;
			if(m.find()){
				icollabel = m.group(1);
				classtype = m.group(2);
				
				if(classtype.equals("String")){
					classtype_obj = String.class;
				}
				else if(classtype.equals("Integer")){
					classtype_obj = Integer.class;
				}
				else if(classtype.equals("Boolean")){
					classtype_obj = Boolean.class;
				}
				else if(classtype.equals("Float")){
					classtype_obj = Float.class;
				}
				else if(classtype.equals("Double")){
					classtype_obj = Double.class;
				}
				else {
					icollabel = each_str;
					classtype = "String";					
					classtype_obj = String.class;
				}
				
			} else {
				icollabel = each_str;
				classtype = "String";
				classtype_obj = String.class;
			}
			
			// System.out.println(icollabel + " - " + classtype);
			icolClasses[i] = classtype_obj;
			icollbs[i]     = icollabel;
		}
	
		HashMap<String, Object[]> ret_h = new HashMap<String, Object[]>();		
		ret_h.put("collabels", icollbs);
		ret_h.put("Classes", icolClasses);
		
		return ret_h;
	}
}
