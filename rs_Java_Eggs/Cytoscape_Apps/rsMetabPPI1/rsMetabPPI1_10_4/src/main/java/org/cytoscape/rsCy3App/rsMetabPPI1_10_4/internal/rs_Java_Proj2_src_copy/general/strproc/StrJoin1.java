package org.cytoscape.rsCy3App.rsMetabPPI1_10_4.internal.rs_Java_Proj2_src_copy.general.strproc;



public class StrJoin1 {

	private String[] strings;

	public StrJoin1(String[] strings){
		this.strings = strings;
	}

	public String strjoin(String glue){

		StringBuilder sb = new StringBuilder();

		for(int i=0; i < strings.length; i++) {
			sb.append(strings[i]);

			if(i < strings.length - 1) {
				sb.append(glue);
			}
		}

		return sb.toString();		

	}

	public static void main(String[] args) {

		String texts[] = {"Hello", "to", "you"};
		StrJoin1 str1 = new StrJoin1(texts);
		System.out.println(str1.strjoin(","));

	}

}
