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

	public int[] relposs_mzs_starts()
			throws IllegalArgumentException {

		int[] relposs = new int[ this.mtimes.size() ];
		
		int[] ms_sizes = this.sizes_ms();
		
		relposs[ 0 ] = 0;
		for(int i = 0; i < this.mtimes.size() - 1; i ++) {
			relposs[ i + 1 ] = relposs[ i ] + ms_sizes[ i ];
		}
		
		return(relposs);
	}
	
	public int[] relposs_mzs_ends()
			throws IllegalArgumentException {

		int[] relposs = new int[ this.mtimes.size() ];

		int[] relpos_mzs_starts = this.relposs_mzs_starts();
		int[] mzs_sizes = this.sizes_mzs();

		for(int i = 0; i < this.mtimes.size(); i ++) {
			relposs[ i ] = relpos_mzs_starts[ i ] + mzs_sizes[ i ] - 1;
		}
		
		return(relposs);
	}
	

	public int[] relposs_intsts_starts()
			throws IllegalArgumentException {

		int[] relposs = new int[ this.mtimes.size() ];
		int[] relpos_mzs_ends = this.relposs_mzs_ends();

		for(int i = 0; i < this.mtimes.size(); i ++) {
			relposs[ i ] = relpos_mzs_ends[ i ] + 1;
		}
		
		return(relposs);
	}
	
	public int[] relposs_intsts_ends()
			throws IllegalArgumentException {

		int[] relposs = new int[ this.mtimes.size() ];
		int[] relpos_intsts_starts = this.relposs_intsts_starts();
		int[] intsts_sizes = this.sizes_intsts();

		for(int i = 0; i < this.mtimes.size(); i ++) {
			relposs[ i ] = relpos_intsts_starts[ i ] + intsts_sizes[ i ] - 1;
		}
		
		return(relposs);
		
	}	

	public int[] sizes_ms()
			throws IllegalArgumentException {
		
		int[] osizes = new int[ this.mtimes.size() ];
		int p = 0;
		
		for(MassSpec_simple1_2<T_mz, T_intst> ms : this.mspecs) {
			osizes[ p ] = ms.bytesize_ms();	
			p ++;
		}
		
		return(osizes);
	}
		
	public int[] sizes_mzs()
			throws IllegalArgumentException {
		
		int[] osizes = new int[ this.mtimes.size() ];
		int p = 0;
		
		for(MassSpec_simple1_2<T_mz, T_intst> ms : this.mspecs) {
			osizes[ p ] = ms.bytesize_mzs();	
			p ++;
		}
		
		return(osizes);
	}
	
	public int[] sizes_intsts()
			throws IllegalArgumentException {
		
		int[] osizes = new int[ this.mtimes.size() ];
		int p = 0;
		
		for(MassSpec_simple1_2<T_mz, T_intst> ms : this.mspecs) {
			osizes[ p ] = ms.bytesize_intsts();			
			p ++;
		}
		
		return(osizes);
	}	
	
	
	
}
