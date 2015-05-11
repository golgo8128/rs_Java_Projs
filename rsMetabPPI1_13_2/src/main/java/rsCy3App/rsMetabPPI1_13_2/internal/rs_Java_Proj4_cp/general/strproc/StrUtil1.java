package rsCy3App.rsMetabPPI1_13_2.internal.rs_Java_Proj4_cp.general.strproc;

public class StrUtil1 {

	public static String strjoin(String[] strs, String sep){
		
		StringBuilder sb = new StringBuilder();

		for(int i=0; i < strs.length; i++) {
			sb.append(strs[i]);

			if(i < strs.length - 1) {
				sb.append(sep);
			}
		}

		return sb.toString();		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] strarray = {"A", "BB", "CCC"};
		
		System.out.println(StrUtil1.strjoin(strarray, "(^_^)"));
	}

}
