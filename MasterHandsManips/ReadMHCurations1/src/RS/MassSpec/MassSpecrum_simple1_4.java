
package RS.MassSpec;

import RS.Usefuls1.VariableType1;
import java.io.DataOutputStream;
import java.io.IOException;

public class MassSpecrum_simple1_4
	<T_mz extends Number, T_intst extends Number>{

	public T_mz[] mzs;
	public T_intst[] intsts;

	private Class<T_mz>klass_T_mz;
	private Class<T_intst>klass_T_intst;

	public void set_Classes(Class<T_mz> iklass_T_mz, Class<T_intst> iklass_intst){
		
		this.klass_T_mz = iklass_T_mz;
		this.klass_T_intst = iklass_intst;
		
	}

	
	public long bytesize_ms() {
		
		return this.bytesize_mzs() + this.bytesize_intsts();
		// Java autoboxing does not seem to work in the intended way here.	

	}
	
	
	public long bytesize_mzs(){
			
		return (long)(VariableType1.<T_mz>get_bsize_vartype(this.klass_T_mz) * this.mzs.length);
		
	}
	
	public long bytesize_intsts(){
	
		return (long)(VariableType1.<T_intst>get_bsize_vartype(this.klass_T_intst) * this.mzs.length);
				
	}
	
	public void output_to_file(DataOutputStream fw) throws IOException {
		
		for(T_mz mz : this.mzs) {
				this.output_to_file_each_mz(fw, mz); // Big endian
		}
		for(T_intst intsty : this.intsts) {
				this.output_to_file_each_intst(fw, intsty); // Big endian
		}
		
	}
	
	public void output_to_file_each_mz (
			DataOutputStream fw,
			T_mz val)
			throws IOException, IllegalArgumentException {
		
		VariableType1.write_single_binary_val_to_file(fw, val);
			
	}
	
	public void output_to_file_each_intst (
			DataOutputStream fw,
			T_intst val)
			throws IOException, IllegalArgumentException {
		
		VariableType1.write_single_binary_val_to_file(fw, val);
		
		
	}
	
	
}
