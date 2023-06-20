
public class TestGeneric3<T> {

	public T testmethod1(Class<T> class1) {
		
		System.out.println(class1.equals(Integer.class));
		
		Long oval = 1L;
		return class1.cast(oval);
		
	}
	
	
	
	public static void main(String[] args) {

		TestGeneric3<Integer> tmpobj = new TestGeneric3<Integer>();
		
		Object tmpanonym = tmpobj.testmethod1(Integer.class);
		System.out.println(Integer.class.isInstance(tmpobj.testmethod1(Integer.class)));
		System.out.println(tmpobj.testmethod1(Integer.class));

	}

}
