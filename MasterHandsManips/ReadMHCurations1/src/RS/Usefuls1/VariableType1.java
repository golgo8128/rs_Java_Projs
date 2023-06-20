package RS.Usefuls1;

import java.io.DataOutputStream;
import java.io.IOException;
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
	
	
	public static byte get_char_symb_from_vartype(Number inumobj) {
		
		byte ochar;
		
		if(Integer.class.isInstance(inumobj)){
			ochar = 'i';
		} else if (Float.class.isInstance(inumobj)) {
			ochar = 'f';
		} else if (Double.class.isInstance(inumobj)) {
			ochar = 'd';
		} else if (Long.class.isInstance(inumobj)) {
			ochar = 'x';
		} else {
			throw new IllegalArgumentException("Illegal data type for finding corresponding char.");
		}
		
		return ochar;
		
	}
	
	
	public static <T> byte get_char_symb_from_vartype(Class<T> iklass) {
		
		byte ochar;
		
		if(Integer.class.equals(iklass)){
			ochar = 'i';
		} else if (Float.class.equals(iklass)) {
			ochar = 'f';
		} else if (Double.class.equals(iklass)) {
			ochar = 'd';
		} else if (Long.class.equals(iklass)) {
			ochar = 'x';
		} else {
			throw new IllegalArgumentException("Illegal data type for finding corresponding char.");
		}
		
		return ochar;
		
	}
	
	
	
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
			throw new IllegalArgumentException("Illegal data type for getting byte size.");
		}
		
		return o_bsize;
		
	}
	
	public static <T> int get_bsize_vartype(Class<T> iklass)
			throws IllegalArgumentException {

		int o_bsize;
		
		if(Integer.class.equals(iklass)){
			o_bsize = Integer.BYTES;
		} else if (Float.class.equals(iklass)) {
			o_bsize = Float.BYTES;
		} else if (Double.class.equals(iklass)) {
			o_bsize = Double.BYTES;
		} else if (Long.class.equals(iklass)) {
			o_bsize = Long.BYTES;
		}
		else {
			throw new IllegalArgumentException("Illegal data type for getting byte size.");
		}
		
		return o_bsize;
		
	}
	
	
	
	
	
	public static void write_single_binary_val_to_file(
			DataOutputStream fw,
			Number iobj) throws IOException {
		
		if(Integer.class.isInstance(iobj)){
			fw.writeInt((int) iobj);
		} else if(Float.class.isInstance(iobj)) {
			fw.writeFloat((float) iobj);
		} else if(Double.class.isInstance(iobj)) {
			fw.writeDouble((double) iobj);
		} else if(Long.class.isInstance(iobj)) {
			fw.writeLong((long) iobj);
		} else {
			throw new IllegalArgumentException("Illegal data type for file writing.");
		}
		
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
