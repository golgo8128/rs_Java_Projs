package RS.MassSpec;

import RS.Usefuls1.VariableType1;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class RSMassSpectra_simpleRW1_1<
	T_mtime extends Number, T_mz extends Number, T_intst extends Number,
	T_numMTs extends Number, T_msdat_bsize extends Number, T_offspos extends Number> {
	
	final int MAGIC_ENDIAN_CHECK = 0x01020304;
	final int VERSION_RS_MULTISPECTRA_DATASTRUCT = 0x00000502;
	
	
	private RS_MassSpectra_simple1_6<T_mtime, T_mz, T_intst,
		T_numMTs, T_msdat_bsize, T_offspos> rsmspectr;
	long flex_header_byte_size;
		
	

	
	// Constructor
	RSMassSpectra_simpleRW1_1(
			RS_MassSpectra_simple1_6<T_mtime, T_mz, T_intst,
				T_numMTs, T_msdat_bsize, T_offspos> irsmspectr, long iflex_header_byte_size,
				T_mtime iexample_mtime){
		
		this.rsmspectr = irsmspectr;
		this.flex_header_byte_size = iflex_header_byte_size;
		
	}
	
	
	public void write_flex_header_to_file(
			DataOutputStream fw) throws IOException {

		
		long cflex_head_size = 0;
		
		// [0-3] Endian check  (integer, int)
		fw.writeInt(MAGIC_ENDIAN_CHECK); // 4 bytes
		cflex_head_size += Integer.BYTES;
		
		// [4-7] VERSION (integer, int)
		fw.writeInt(VERSION_RS_MULTISPECTRA_DATASTRUCT); // 4 bytes		
		cflex_head_size += Integer.BYTES;
		
		// [8-15] Offset from the start to the spectra header (integer, long)
		fw.writeLong(this.flex_header_byte_size); // 8 bytes
		cflex_head_size += Long.BYTES;
		
		// Variable types
		fw.writeByte(VariableType1.get_char_symb_from_vartype(this.rsmspectr.example_mtime));
		cflex_head_size += Byte.BYTES;
		
		T_mz mz_dummy = null;
		fw.writeByte(VariableType1.get_char_symb_from_vartype(mz_dummy));
		cflex_head_size += Byte.BYTES;
		
		T_intst intst_dummy = null;
		fw.writeByte(VariableType1.get_char_symb_from_vartype(intst_dummy));
		cflex_head_size += Byte.BYTES;
		
		T_numMTs nummts_dummy = null;
		fw.writeByte(VariableType1.get_char_symb_from_vartype(nummts_dummy));
		cflex_head_size += Byte.BYTES;
		
		T_msdat_bsize msdat_bsize_dummy = null;
		fw.writeByte(VariableType1.get_char_symb_from_vartype(msdat_bsize_dummy));
		cflex_head_size += Byte.BYTES;		
		
		T_offspos offspos_dummy = null;
		fw.writeByte(VariableType1.get_char_symb_from_vartype(offspos_dummy));
		cflex_head_size += Byte.BYTES;				
		
		
		// Padding by 0's
		for(long i = cflex_head_size;
				i < this.flex_header_byte_size;i ++) { 
			fw.writeByte(0x00);
		}
		
	}
	
	
	public long get_spectra_header_bsize()
			throws IllegalArgumentException {
		
		
		T_numMTs nummts_dummy = null;
		T_mtime mt_dummy = null;
		T_offspos offspos_dummy = null;
		T_msdat_bsize msdat_bsize_dummy = null;
		
		return 
				+ VariableType1.get_bsize_vartype(nummts_dummy)
				+ this.rsmspectr.mtimes.size() * VariableType1.get_bsize_vartype(mt_dummy)
				+ this.rsmspectr.mtimes.size() * VariableType1.get_bsize_vartype(offspos_dummy) * 2
				+ this.rsmspectr.mtimes.size() * VariableType1.get_bsize_vartype(msdat_bsize_dummy) * 2;		

	}
	

	
	public void write_spectra_header_to_file(DataOutputStream fw)
			throws IOException{
		
		long header_bytes = this.flex_header_byte_size + this.get_spectra_header_bsize();
	
		
		T_mtime mt_dummy;
		
		for(T_mtime mt : this.rsmspectr.mtimes) {
			if(Integer.class.isInstance(mt_dummy)){
				fw.writeInt((int)mt);
			} else if (Float.class.isInstance(mt_dummy)) {
				fw.writeFloat((float)mt);
			} else if (Double.class.isInstance(mt_dummy)) {
				fw.writeDouble((double)mt);
			} else if (Long.class.isInstance(mt_dummy)) {
				fw.writeLong((long)mt);
			} else {
				throw new IllegalArgumentException("Illegal data type for MT's.");
			}
		}
		
		for(long relpos : this.relposs_mzs_starts()) {
			if(this.vartype_symb_relpos == 'i') {
				fw.writeInt((int)(relpos + header_bytes));
			} else {
				fw.writeLong(relpos + header_bytes);
			} 
		}
		
		for(int csize : this.sizes_mzs()) {
			fw.writeInt(csize);
		}
		
		for(long relpos : this.relposs_intsts_starts()) {
			if(this.vartype_symb_relpos == 'i') {
				fw.writeInt((int)(relpos + header_bytes));
			} else  {
				fw.writeLong(relpos + header_bytes);
			}
		}
		
		for(int csize : this.sizes_intsts()) {
			fw.writeInt(csize);
		}
		
	}
	
	
	public void output_to_file(Path opath, long flex_header_byte_size) throws IOException {
		
		Files.createDirectories(opath.getParent());
		
		DataOutputStream fw = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(opath.toString())));
		
		this.write_foffset(fw, flex_header_byte_size);
		fw.writeInt(this.mtimes.size());
		this.write_header_to_file(fw, foffset_byte_size);
		
		for(MassSpecrum_simple1_3<T_mz, T_intst, T_msdat_bsize>mspec : this.mspecs) {
			mspec.output_to_file(fw);			
		}
		
		fw.close();
		
	}
	
	
	
	public long[] relposs_mzs_starts()
			throws IllegalArgumentException {

		long[] relposs = new long[ this.rsmspectr.mtimes.size() ];
				
		relposs[ 0 ] = 0;
		for(long i = 0; i < this.rsmspectr.mtimes.size() - 1; i ++) {			
			relposs[ (int) (i + 1) ] = relposs[ (int) i ]
					+ (long)this.rsmspectr.mspecs.get((int)i).bytesize_ms();
		}
		
		return(relposs);
	}
	
	public long[] relposs_mzs_ends()
			throws IllegalArgumentException {

		long[] relposs = new long[ this.rsmspectr.mtimes.size() ];
		long[] relpos_mzs_starts = this.relposs_mzs_starts();

		for(long i = 0; i < this.rsmspectr.mtimes.size(); i ++) {
			relposs[ (int) i ] = relpos_mzs_starts[ (int) i ]
					+ (long)this.rsmspectr.mspecs.get((int)i).bytesize_mzs() - 1;
		}
		
		return(relposs);
	}
	

	public long[] relposs_intsts_starts()
			throws IllegalArgumentException {

		long[] relposs = new long[ this.rsmspectr.mtimes.size() ];
		long[] relpos_mzs_ends = this.relposs_mzs_ends();

		for(long i = 0; i < this.rsmspectr.mtimes.size(); i ++) {
			relposs[ (int) i ] = relpos_mzs_ends[ (int) i ] + 1;
		}
		
		return(relposs);
	}
	
	public long[] relposs_intsts_ends()
			throws IllegalArgumentException {

		long[] relposs = new long[ this.rsmspectr.mtimes.size() ];
		long[] relpos_intsts_starts = this.relposs_intsts_starts();

		for(long i = 0; i < this.rsmspectr.mtimes.size(); i ++) {
			relposs[ (int) i ] = relpos_intsts_starts[ (int) i ] +
					(long)this.rsmspectr.mspecs.get((int) i).bytesize_intsts() - 1;
					
		}
		
		return(relposs);
		
	}	

	public ArrayList<T_msdat_bsize> sizes_ms()
			throws IllegalArgumentException {
		
		ArrayList<T_msdat_bsize> osizes = new ArrayList<T_msdat_bsize>();
		
		for(MassSpecrum_simple1_3<T_mz, T_intst, T_msdat_bsize> ms : this.rsmspectr.mspecs) {
			osizes.add(ms.bytesize_ms());
		}
		
		return(osizes);
	}
		
	public ArrayList<T_msdat_bsize> sizes_mzs()
			throws IllegalArgumentException {
		
		ArrayList<T_msdat_bsize> osizes = new ArrayList<T_msdat_bsize>();
		
		for(MassSpecrum_simple1_3<T_mz, T_intst, T_msdat_bsize> ms : this.rsmspectr.mspecs) {
			osizes.add(ms.bytesize_mzs());	
		}
		
		return(osizes);
	}
	
	public ArrayList<T_msdat_bsize> sizes_intsts()
			throws IllegalArgumentException {
		
		ArrayList<T_msdat_bsize> osizes = new ArrayList<T_msdat_bsize>();
		
		for(MassSpecrum_simple1_3<T_mz, T_intst, T_msdat_bsize> ms : this.rsmspectr.mspecs) {
			osizes.add(ms.bytesize_intsts());			
		}
		
		return(osizes);
		
	}	

}
