
import java.io.DataOutputStream;
import java.io.IOException;

public class MassSpec_simple1_2 <T_mz, T_intst>{

	public T_mz[] mzs;
	public T_intst[] intsts;

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
		
		if(Integer.class.isInstance(val)){
			fw.writeInt((int) val);
		} else if(Float.class.isInstance(val)) {
			fw.writeFloat((float) val);
		} else if(Double.class.isInstance(val)) {
			fw.writeDouble((double) val);
		} else {
			throw new IllegalArgumentException("Writing to file failed.");
		}
		
	}
	
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
		} else {
			throw new IllegalArgumentException("Writing to file failed.");
		}
		
	}
	
		
}
