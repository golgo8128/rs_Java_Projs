package RS.Usefuls1;

import java.util.HashMap;

public class VariableType1 {
	

	/*
	public static char get_symb_from_varnam(String ivarname)
	{
	    if (varname_to_symb_h.ContainsKey(ivarname))
	    {
	        return varname_to_symb_h[ivarname];
	    }
	    else
	    {
	        throw new Exception($"Variable name \"{ivarname}\" not found");
	    }
	
	}
	*/
	
	
	public static int get_bsize_vartype(Number iobj)
			throws IllegalArgumentException {


		int o_bsize;
		
		if(Integer.class.isInstance(iobj)){
			o_bsize = 4;
		} else if (Float.class.isInstance(iobj)) {
			o_bsize = 4;
		} else if (Double.class.isInstance(iobj)) {
			o_bsize = 8;
		} else if (Long.class.isInstance(iobj)) {
			o_bsize = 8;
		}
		else {
			throw new IllegalArgumentException("Illegal data type for m/z's.");
		}
		
		return o_bsize;
		
	}
	

	/*
	static void Main()
	{
	
	    Console.WriteLine($"{get_symb_from_varnam("Int32")}");
	    Console.WriteLine($"{get_symb_from_varnam("Double")}");
	    Console.WriteLine("Hello World!");
	    Console.ReadKey();
	
	}
	*/

}
