package testFolder.reflection1;

import java.lang.reflect.Method;

public class GetSelfMethod_test1 {

	public static String str_method1(){
		return new String("Hello");
	}

	public static String str_method2(){
		return new String("Hi");
	}	

	public static String str_method3(){
		return new String("Bye");
	}	

	public static void tmp_method1(){
		
	}	

	public static void tmp_method2(){
		
	}	
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            Class<?> c = GetSelfMethod_test1.class;
            Method m[] = c.getDeclaredMethods();
            for (int i = 0; i < m.length; i++){
	            System.out.println(m[i].toString());
	            if(m[i].toString().contains("str_method")){
	                System.out.println("Got it!");
	                System.out.println(m[i].invoke(null));
	            }
            }
		}
		catch (Throwable e) {
		    System.err.println(e);
		}	
	}

}

