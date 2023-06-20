
public class TestGeneric2<T> {

	public Integer testmethod1() {
		
		long oval = 1L;
		// System.out.println(Integer.class.isInstance((T)oval));
		return (int)oval;
		
	}
	
	
	
	public static void main(String[] args) {

		TestGeneric2<Integer> tmpobj = new TestGeneric2<Integer>();
		
		Object tmpanonym = tmpobj.testmethod1();
		
		System.out.println(Integer.class.isInstance(tmpobj.testmethod1()));
		System.out.println(tmpobj.testmethod1());

	}

}
