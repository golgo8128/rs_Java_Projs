package rsCy3App.Cy3AutoReadTSV1_1.internal.rs_Java_Proj4_cp.general.strproc;

import java.util.HashSet;
import java.util.Set;

public class SumUpUntilAvail1 {

	private final String formatStr;
	private static final int COUNTMAX = 10000;
	
	public SumUpUntilAvail1(String formatStr) {
		// TODO Auto-generated constructor stub
		this.formatStr = formatStr;
	}

	public String get_avail(Set<String> iStrs){
		
		for(int i = 0;i < COUNTMAX;i ++){
			String checkStr = String.format(formatStr, i+1);
			if(!iStrs.contains(checkStr)){
				return(checkStr);
			}
		}
		
		return("Tired."); // Lazy ...
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SumUpUntilAvail1 suua = new SumUpUntilAvail1("Car %d");
		HashSet<String> hstest = new HashSet<String>();
		hstest.add("Car 1");
		hstest.add("Car 2");
		hstest.add("Car 3");
		hstest.add("Car 4");
		hstest.add("Car 5");
		
		System.out.println(suua.get_avail(hstest));
		
	}

}
