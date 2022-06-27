package RS.MassSpec;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class RS_MassSpectra_simple1_3 <T_mtime, T_mz, T_intst, T_rpos extends Number>{

	public ArrayList<T_mtime> mtimes;
	public ArrayList<MassSpecrum_simple1_2<T_mz, T_intst>> mspecs;
	private T_rpos calibr_pos_zero;
	
	public RS_MassSpectra_simple1_3(T_rpos ipos_zero) {
		
		this.mtimes   = new ArrayList<T_mtime>();
		this.mspecs   = new ArrayList<MassSpecrum_simple1_2<T_mz, T_intst>>();
		
		if(ipos_zero.longValue() != 0) {
			throw new IllegalArgumentException(
					String.format("Calibrated position not zero: %d", ipos_zero.longValue()));
		}
		
		this.calibr_pos_zero = ipos_zero;
		
	}	

	public void add_ms(T_mtime imt, MassSpecrum_simple1_2<T_mz, T_intst> ms){
		
		this.mtimes.add(imt);
		this.mspecs.add(ms);


	}
	
	public void add_ms(T_mtime imt, T_mz[] mzs, T_intst[] intsts){
		
		this.mtimes.add(imt);
		
		MassSpecrum_simple1_2<T_mz, T_intst> ms = new MassSpecrum_simple1_2<T_mz, T_intst>();
		ms.mzs    = mzs;
		ms.intsts = intsts;
		this.mspecs.add(ms);

	}
	
	
	public void output_to_file(Path opath, int foffset_byte_size) throws IOException {
		
		Files.createDirectories(opath.getParent());
		
		DataOutputStream fw = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(opath.toString())));
		
		this.write_foffset(fw, foffset_byte_size);
		fw.writeInt(this.mtimes.size());
		this.write_header_to_file(fw, foffset_byte_size);
		
		for(MassSpecrum_simple1_2<T_mz, T_intst>mspec : this.mspecs) {
			mspec.output_to_file(fw);			
		}
		
		fw.close();
		
	}

	public int get_header_bytes(int foffset_byte_size)
			throws IllegalArgumentException {
		
		if(this.mtimes.size() == 0) {
			return foffset_byte_size;
		}
		
		T_mtime mt_1st = this.mtimes.get(0);
		int mtime_byte_size;
		
		if(Integer.class.isInstance(mt_1st)){
			mtime_byte_size = 4;
		} else if (Float.class.isInstance(mt_1st)) {
			mtime_byte_size = 4;
		} else if (Double.class.isInstance(mt_1st)) {
			mtime_byte_size = 8;
		} else if (Long.class.isInstance(mt_1st)) {
			mtime_byte_size = 8;
		} else {
			throw new IllegalArgumentException("Illegal data type for MT's.");
		}
			
		return foffset_byte_size
				+ 4 // <--- Number of spectra is represented by the type int.
				+ mtimes.size() * mtime_byte_size
				+ mtimes.size() * Integer.BYTES * 4; // <--- Assumes that relative positions are expressed in the type int.

	}
	
	public void write_foffset(DataOutputStream fw,
			int foffset_byte_size) throws IOException {
		
		fw.writeInt(foffset_byte_size); // 4 bytes
		fw.writeInt(0x01020304); // 4 bytes
		
		// 1 byte
		T_mtime mt_1st = this.mtimes.get(0);
		if(Integer.class.isInstance(mt_1st)){
			fw.writeByte('i');
		} else if (Float.class.isInstance(mt_1st)) {
			fw.writeByte('f');
		} else if (Double.class.isInstance(mt_1st)) {
			fw.writeByte('d');
		} else if (Long.class.isInstance(mt_1st)) {
			fw.writeByte('x');
		} else {
			throw new IllegalArgumentException("Illegal data type for MT's.");
		}

		// 1 byte
		T_mz mz_1st1st = this.mspecs.get(0).mzs[0];
		if(Integer.class.isInstance(mz_1st1st)){
			fw.writeByte('i');
		} else if (Float.class.isInstance(mz_1st1st)) {
			fw.writeByte('f');
		} else if (Double.class.isInstance(mz_1st1st)) {
			fw.writeByte('d');
		} else if (Long.class.isInstance(mz_1st1st)) {
			fw.writeByte('x');
		} else {
			throw new IllegalArgumentException("Illegal data type for m/z's.");
		}		

		// 1 byte
		T_intst intst_1st1st = this.mspecs.get(0).intsts[0];
		if(Integer.class.isInstance(intst_1st1st)){
			fw.writeByte('i');
		} else if (Float.class.isInstance(intst_1st1st)) {
			fw.writeByte('f');
		} else if (Double.class.isInstance(intst_1st1st)) {
			fw.writeByte('d');
		} else if (Long.class.isInstance(intst_1st1st)) {
			fw.writeByte('x');
		} else {
			throw new IllegalArgumentException("Illegal data type for intensities.");
		}	
		
		
		for(int i = 12;i < foffset_byte_size;i ++) { // BE CAREFUL ... Number of bytes
			fw.writeByte(0x00);
		}
		
	}
	
	public void write_header_to_file(DataOutputStream fw, int foffset_byte_size)
			throws IOException{
		
		int header_bytes = this.get_header_bytes(foffset_byte_size);
		
		
		if(this.mtimes.size() == 0) {
			return;
		}
		
		T_mtime mt_1st = this.mtimes.get(0);
		
		for(T_mtime mt : this.mtimes) {
			if(Integer.class.isInstance(mt_1st)){
				fw.writeInt((int)mt);
			} else if (Float.class.isInstance(mt_1st)) {
				fw.writeFloat((float)mt);
			} else if (Double.class.isInstance(mt_1st)) {
				fw.writeDouble((double)mt);
			} else if (Long.class.isInstance(mt_1st)) {
				fw.writeLong((long)mt);
			} else {
				throw new IllegalArgumentException("Illegal data type for MT's.");
			}
		}
		
		for(T_rpos relpos : this.relposs_mzs_starts()) {
			if(Integer.class.isInstance(relpos)){
				fw.writeInt(relpos.intValue() + header_bytes);
			} else if (Long.class.isInstance(relpos)) {
				fw.writeLong(relpos.longValue() + header_bytes);
			} else {
				throw new IllegalArgumentException("Illegal data type for relative positions.");
			}
		}
		
		for(int csize : this.sizes_mzs()) {
			fw.writeInt(csize);
		}
		
		for(T_rpos relpos : this.relposs_intsts_starts()) {
			if(Integer.class.isInstance(relpos)){
				fw.writeInt(relpos.intValue() + header_bytes);
			} else if (Long.class.isInstance(relpos)) {
				fw.writeLong(relpos.longValue() + header_bytes);
			} else {
				throw new IllegalArgumentException("Illegal data type for relative positions.");
			}
		}
		
		for(int csize : this.sizes_intsts()) {
			fw.writeInt(csize);
		}
		
	}
	
	public T_rpos[] relposs_mzs_starts()
			throws IllegalArgumentException { // Maybe use List instead of array

		T_rpos[] relposs = new T_rpos[ this.mtimes.size() ];
		
		int[] ms_sizes = this.sizes_ms();
		
		relposs[ 0 ] = (T_rpos)new Integer(0); // (this.calibr_pos_zero;
		
		
		for(int i = 0; i < this.mtimes.size() - 1; i ++) {
			relposs[ i + 1 ] = relposs[ i ] + ms_sizes[ i ];
		}
		
		return(relposs);
	}
	
	public T_rpos[] relposs_mzs_ends()
			throws IllegalArgumentException {

		T_rpos[] relposs = new T_rpos[ this.mtimes.size() ];

		T_rpos[] relpos_mzs_starts = this.relposs_mzs_starts();
		int[] mzs_sizes = this.sizes_mzs();

		for(int i = 0; i < this.mtimes.size(); i ++) {
			relposs[ i ] = relpos_mzs_starts[ i ] + mzs_sizes[ i ] - 1;
		}
		
		return(relposs);
	}
	

	public T_rpos[] relposs_intsts_starts()
			throws IllegalArgumentException {

		T_rpos[] relposs = new T_rpos[ this.mtimes.size() ];
		T_rpos[] relpos_mzs_ends = this.relposs_mzs_ends();

		for(int i = 0; i < this.mtimes.size(); i ++) {
			relposs[ i ] = relpos_mzs_ends[ i ] + 1;
		}
		
		return(relposs);
	}
	
	public T_rpos[] relposs_intsts_ends()
			throws IllegalArgumentException {

		T_rpos[] relposs = new T_rpos[ this.mtimes.size() ];
		T_rpos[] relpos_intsts_starts = this.relposs_intsts_starts();
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
		
		for(MassSpecrum_simple1_2<T_mz, T_intst> ms : this.mspecs) {
			osizes[ p ] = ms.bytesize_ms();	
			p ++;
		}
		
		return(osizes);
	}
		
	public int[] sizes_mzs()
			throws IllegalArgumentException {
		
		int[] osizes = new int[ this.mtimes.size() ];
		int p = 0;
		
		for(MassSpecrum_simple1_2<T_mz, T_intst> ms : this.mspecs) {
			osizes[ p ] = ms.bytesize_mzs();	
			p ++;
		}
		
		return(osizes);
	}
	
	public int[] sizes_intsts()
			throws IllegalArgumentException {
		
		int[] osizes = new int[ this.mtimes.size() ];
		int p = 0;
		
		for(MassSpecrum_simple1_2<T_mz, T_intst> ms : this.mspecs) {
			osizes[ p ] = ms.bytesize_intsts();			
			p ++;
		}
		
		return(osizes);
	}	
	
	
	
}
