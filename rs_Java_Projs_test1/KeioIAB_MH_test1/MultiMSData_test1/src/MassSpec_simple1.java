
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MassSpec_simple1 {

	public float[] mzs;
	public int[] intsties;

	public void output_to_file(DataOutputStream fw) {

		try {
			for(float mz : this.mzs) {
				fw.writeFloat(mz);
			}
			for(int intsty : this.intsties) {
				fw.writeInt(intsty);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
