import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MultiMS_simple1 {

	public float[] mtimes;
	public MassSpec_simple1[] mspecs;
	
	public int cnum_mss;
	public int num_max_mss;
	
	public MultiMS_simple1(int num_all_mss) {
		
		this.cnum_mss = 0;
		this.num_max_mss = num_all_mss;
		this.mtimes   = new float[ num_all_mss ];
		this.mspecs   = new MassSpec_simple1[ num_all_mss ];
		
	}	

	public void add_ms(float imt, MassSpec_simple1 ms)
			throws IndexOutOfBoundsException {
		
		if(this.cnum_mss < this.num_max_mss) {
			this.mtimes[ this.cnum_mss ] = imt;
			this.mspecs[ this.cnum_mss ] = ms;
			this.cnum_mss ++;
		} else {
			throw new IndexOutOfBoundsException("Mass spec. overflow");
			// Unchecked exception. Treating this is optional.
		}

	}
	
	public void output_to_file(Path opath) throws IOException {
		
		Files.createDirectories(opath.getParent());
		
		DataOutputStream fw = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(opath.toString())));
		
		for(int i = 0;i < this.cnum_mss;i ++) {
			this.mspecs[ i ].output_to_file(fw);
		}
		
		fw.close();
		
	}
	
	
}
