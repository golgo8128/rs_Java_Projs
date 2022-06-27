
public class generics_test_call1 {

	 public static void main(String args[]){
	
		 GenericAdderTest1<Long> gat_test = new GenericAdderTest1<Long>();
		 
		 System.out.println(String.format("%d",  gat_test.zero()));
		 System.out.println(String.format("%d",  gat_test.add(10L, 20L)));
		 
	 }
	
}
