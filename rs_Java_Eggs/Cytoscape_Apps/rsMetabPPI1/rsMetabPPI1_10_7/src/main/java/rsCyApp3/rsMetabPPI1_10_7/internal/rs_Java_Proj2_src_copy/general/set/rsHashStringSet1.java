package rsCyApp3.rsMetabPPI1_10_7.internal.rs_Java_Proj2_src_copy.general.set;


import java.util.HashSet;

public class rsHashStringSet1 extends HashSet<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4357970930238197411L;

	public rsHashStringSet1() {
		// TODO Auto-generated constructor stub
	}

	public rsHashStringSet1(String[] istrs) {
		// TODO Auto-generated constructor stub
		for(String istr: istrs){
			this.add(istr);
		}
	}	
	
//	public rsHashSet1(Collection<? extends Object> arg0) {
//		super(arg0);
//		// TODO Auto-generated constructor stub
//	}
//
//	public rsHashSet1(int arg0) {
//		super(arg0);
//		// TODO Auto-generated constructor stub
//	}
//
//	public rsHashSet1(int arg0, float arg1) {
//		super(arg0, arg1);
//		// TODO Auto-generated constructor stub
//	}

	/**
	 * @param args
	 */
	
	public rsHashStringSet1 diff(HashSet<String> iset){
		
		rsHashStringSet1 diffset = new rsHashStringSet1();
		
		for(String elem: this){
			if(!iset.contains(elem)){
				diffset.add(elem);
			}
		}
		
		return diffset;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] set1str = {"AA","BB","CC","DD","EE"};
		rsHashStringSet1 set1 = new rsHashStringSet1(set1str);
//		set1.add("AA");
//		set1.add("BB");
//		set1.add("CC");		
//		set1.add("DD");
//		set1.add("EE");

		HashSet<String> set2 = new HashSet<String>();
		set2.add("CC");		
		set2.add("DD");
		set2.add("EE");
		set2.add("FF");
		set2.add("GG");		
		
		rsHashStringSet1 setdiff = set1.diff(set2);
				
		for(String elem : setdiff){
			System.out.println(elem);
		}
		
		System.out.println("Random 2th:" + set1.toArray()[2]);
		
	}

}
