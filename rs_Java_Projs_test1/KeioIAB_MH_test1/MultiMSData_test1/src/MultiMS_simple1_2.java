import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MultiMS_simple1_2 <T_mtime, T_mz, T_intst>{

	public ArrayList<T_mtime> mtimes;
	public ArrayList<MassSpec_simple1_2<T_mz, T_intst>> mspecs;
	
	public MultiMS_simple1_2() {
		
		this.mtimes   = new ArrayList<T_mtime>();
		this.mspecs   = new ArrayList<MassSpec_simple1_2<T_mz, T_intst>>();
		
	}	

	public void add_ms(T_mtime imt, MassSpec_simple1_2<T_mz, T_intst> ms){
		
		this.mtimes.add(imt);
		this.mspecs.add(ms);


	}
	
	public void add_ms(T_mtime imt, T_mz[] mzs, T_intst[] intsts){
		
		this.mtimes.add(imt);
		
		MassSpec_simple1_2<T_mz, T_intst> ms = new MassSpec_simple1_2<T_mz, T_intst>();
		ms.mzs    = mzs;
		ms.intsts = intsts;
		this.mspecs.add(ms);

	}
	
	
	public void output_to_file(Path opath) throws IOException {
		
		Files.createDirectories(opath.getParent());
		
		DataOutputStream fw = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(opath.toString())));
		
		for(MassSpec_simple1_2<T_mz, T_intst>mspec : this.mspecs) {
			mspec.output_to_file(fw);			
		}
		
		fw.close();
		
	}
	
	public int[] relpos_sizes_mzs()
			throws IllegalArgumentException {
		
		int[] osizes = new int[ this.mtimes.size() ];
		int p = 0;
		
		for(MassSpec_simple1_2<T_mz, T_intst> ms : this.mspecs) {
				
			if(ms.mzs.length > 0) {
				T_mz mz1st = ms.mzs[0];
				
				if(Integer.class.isInstance(mz1st)){
					osizes[ p ] = 4 * ms.mzs.length;
				} else if (Float.class.isInstance(mz1st)) {
					osizes[ p ] = 4 * ms.mzs.length;
				} else if (Double.class.isInstance(mz1st)) {
					osizes[ p ] = 8 * ms.mzs.length;
				} else {
					throw new IllegalArgumentException("Illegal data type for m/z's.");
				}
					
			}
			
			p ++;
		}
		
		return(osizes);
	}
	
	public int[] relpos_sizes_intsts()
			throws IllegalArgumentException {
		
		int[] osizes = new int[ this.mtimes.size() ];
		int p = 0;
		
		for(MassSpec_simple1_2<T_mz, T_intst> ms : this.mspecs) {
				
			if(ms.intsts.length > 0) {
				T_intst intst = ms.intsts[0];
				
				if(Integer.class.isInstance(intst)){
					osizes[ p ] = 4 * ms.intsts.length;
				} else if (Float.class.isInstance(intst)) {
					osizes[ p ] = 4 * ms.intsts.length;
				} else if (Double.class.isInstance(intst)) {
					osizes[ p ] = 8 * ms.intsts.length;
				} else {
					throw new IllegalArgumentException("Illegal data type for intensities.");
				}
			}
			
			p ++;
		}
		
		return(osizes);
	}	
	
	
	
}
