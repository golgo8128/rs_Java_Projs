
import java.io.DataOutputStream;
import java.io.IOException;

public class MassSpec_simple1 {

	public float[] mzs;
	public int[] intsties;

	public void output_to_file(DataOutputStream fw) throws IOException {

		for(float mz : this.mzs) {
				fw.writeFloat(mz); // Big endian
		}
		for(int intsty : this.intsties) {
				fw.writeInt(intsty); // Big endian
		}
		
	}
		
}
