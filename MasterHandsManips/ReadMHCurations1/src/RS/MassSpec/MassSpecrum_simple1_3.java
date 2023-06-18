
package RS.MassSpec;

import RS.Usefuls1.VariableType1;
import java.io.DataOutputStream;
import java.io.IOException;

public class MassSpecrum_simple1_3
	<T_mz extends Number, T_intst extends Number, T_msdat_bsize extends Number>{

	public T_mz[] mzs;
	public T_intst[] intsts;

	
	public T_msdat_bsize bytesize_ms() {
		
		// return this.bytesize_mzs() + this.bytesize_intsts();
		// Java autoboxing does not seem to work in the intended way here.
		
		
		Long oval = (Long)this.bytesize_mzs() + (Long)this.bytesize_intsts();
		// Long oval = this.bytesize_mzs().longValue() + this.bytesize_intsts().longValue();
		// Long oval = this.bytesize_mzs_ret_long() + this.bytesize_intsts_ret_long();
		
		
		return (T_msdat_bsize)oval;
		
	}
	

	
	public T_msdat_bsize bytesize_mzs(){
			
		T_mz mz1st_dummy = null;
		Long oval = (long)(VariableType1.get_bsize_vartype(mz1st_dummy) * this.mzs.length);
		return (T_msdat_bsize)oval;
		
	}
	
	/*
	public long bytesize_mzs_ret_long() 
		throws IllegalArgumentException {

		T_mz mz1st_dummy = null;
		return VariableType1.get_bsize_vartype(mz1st_dummy) * this.mzs.length;
		
	}
	*/


	public T_msdat_bsize bytesize_intsts(){
	
		T_intst intsts1st_dummy = null;
		Long oval = (long)VariableType1.get_bsize_vartype(intsts1st_dummy) * this.mzs.length;
		return (T_msdat_bsize)oval;
				
	}
	
	/*
	public long bytesize_intsts_ret_long() 
			throws IllegalArgumentException {

		T_intst intsts1st_dummy = null;
		return VariableType1.get_bsize_vartype(intsts1st_dummy) * this.mzs.length;
			
	}	
	*/
	
	public void output_to_file(DataOutputStream fw) throws IOException {
		
		for(T_mz mz : this.mzs) {
				this.output_to_file_each_mz(fw, mz); // Big endian
		}
		for(T_intst intsty : this.intsts) {
				this.output_to_file_each_intst(fw, intsty); // Big endian
		}
		
	}
	
	// Maybe you can also consider to use overloading.
	public void output_to_file_each_mz (
			DataOutputStream fw,
			T_mz val)
			throws IOException, IllegalArgumentException {
		
		if(Integer.class.isInstance(val)){
			fw.writeInt((int) val);
		} else if(Float.class.isInstance(val)) {
			fw.writeFloat((float) val);
		} else if(Double.class.isInstance(val)) {
			fw.writeDouble((double) val);
		} else if(Long.class.isInstance(val)) {
			fw.writeLong((long) val);
		} else {
			throw new IllegalArgumentException("Writing to file failed.");
		}
		
	}
	
	// Maybe you can also consider to use overloading.
	public void output_to_file_each_intst (
			DataOutputStream fw,
			T_intst val)
			throws IOException, IllegalArgumentException {
		
		if(Integer.class.isInstance(val)){
			fw.writeInt((int) val);
		} else if(Float.class.isInstance(val)) {
			fw.writeFloat((float) val);
		} else if(Double.class.isInstance(val)) {
			fw.writeDouble((double) val);
		} else if(Long.class.isInstance(val)) {
			fw.writeLong((long) val);
		} else {
			throw new IllegalArgumentException("Writing to file failed.");
		}
		
	}
	
	
}
