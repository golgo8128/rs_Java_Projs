package testFolder.reflection1;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinOutputManyMethods2 {
		
	public static Object[][] assemble_ObjArrayParts(){

		ArrayList<Object[]> arrayList = new ArrayList<Object[]>(); 	
		
		try {
			Class<?> klass = JoinOutputManyMethods2.class; // Name of this class itself
			Method method[] = klass.getDeclaredMethods();
			for (int i = 0; i < method.length; i++){
				System.out.println(method[i].toString());
				if(method[i].toString().contains("getObjArrayPart")){
					List<Object[]> list = Arrays.asList((Object[][])method[i].invoke(null));
					arrayList.addAll(list);
				}
			}
		}
		catch (Throwable e) {
			    System.err.println(e);
		}	
	
		return (Object[][])arrayList.toArray(new Object[0][0]);
		
	}
	
	private static Object[][] getObjArrayPart1(){
		
		final Object[][] objarray = {
			{ 10, "abc", 20, "defg", 30 },
			{ 40, "def" }
		};
			
		return objarray;	
	}

	private static Object[][] getObjArrayPart2(){
		
		final Object[][] objarray = {
			{ 10, "abc", 20, "defg", 30 },
			{ 40, "xxx" }
		};
			
		return objarray;	
	}	

	private static Object[][] getObjArrayPart3(){
		
		final Object[][] objarray = {
			{ 10, "abc", 20, "defg", 30 },
			{ 40, "yyy" }
		};
			
		return objarray;	
	}		
	
	
	public static void main(String[] args) {

		Object obj2d[][] = assemble_ObjArrayParts();
		for(Object[] obj1d:obj2d){
			System.out.println(obj1d);
			for(Object obj:obj1d){
				System.out.println(obj);
			}
		}
		
	}

}
