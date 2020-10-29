package rsCy3App.rsMetabPPI1_10_2.internal.rs_Java_Proj2_src_copy.general.strproc;


import java.util.ArrayList;

public class LineSplit1 {

	private String itext;
	
	public LineSplit1(String itext) {

		this.itext = itext;
	}
	
	public String[] linesplit(String stripmode){
		
        ArrayList<String> lines = new ArrayList<String>();
        String[] strs = itext.split("\n");
        
        for(String str1_raw: strs){
        	String str1;
        	if(stripmode.equals("strip")){
        		str1 = str1_raw.trim();
        	} else {
        		str1 = str1_raw;
        	}
            lines.add(str1);        	
        	
        }
        
        return lines.toArray(new String[lines.size()]);
		
	}
	
	public static void main(String[] args) {

		LineSplit1 linesplit = new LineSplit1("aaaaa\nbbbb  \n  ccc\nddd\n");
		String[] strs1 = linesplit.linesplit("strip");
		
		for(String str1: strs1){
			System.out.println(":" + str1 + ":");
		}

	}

}
