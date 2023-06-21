package RS.MassSpec;

import RS.Usefuls1.VariableType1;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class RSMassSpectra_simpleRW1_2<
	T_mtime extends Number, T_mz extends Number, T_intst extends Number,
	T_numMTs extends Number, T_msdat_bsize extends Number, T_offspos extends Number> {
	
	final int MAGIC_ENDIAN_CHECK = 0x01020304;
	final int VERSION_RS_MULTISPECTRA_DATASTRUCT = 0x00000502;
	
	
	private RS_MassSpectra_simple1_7<T_mtime, T_mz, T_intst,
		T_numMTs, T_msdat_bsize, T_offspos> rsmspectr;
	long flex_header_byte_size;
		
	

	
	// Constructor
	public RSMassSpectra_simpleRW1_2(
			RS_MassSpectra_simple1_7<T_mtime, T_mz, T_intst,
				T_numMTs, T_msdat_bsize, T_offspos> irsmspectr, long iflex_header_byte_size){
		
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
		
		/* ----- Variable types ----- */
		fw.writeByte(VariableType1.<T_mtime>get_char_symb_from_vartype(this.rsmspectr.klass_T_mtime));
		cflex_head_size += Byte.BYTES;
		
		fw.writeByte(VariableType1.<T_mz>get_char_symb_from_vartype(this.rsmspectr.klass_T_mz));
		cflex_head_size += Byte.BYTES;
		
		fw.writeByte(VariableType1.<T_intst>get_char_symb_from_vartype(this.rsmspectr.klass_T_intst));
		cflex_head_size += Byte.BYTES;
		
		fw.writeByte(VariableType1.<T_numMTs>get_char_symb_from_vartype(this.rsmspectr.klass_T_numMTs));
		cflex_head_size += Byte.BYTES;
		
		fw.writeByte(VariableType1.<T_msdat_bsize>get_char_symb_from_vartype(this.rsmspectr.klass_T_msdat_bsize));
		cflex_head_size += Byte.BYTES;		
		
		fw.writeByte(VariableType1.<T_offspos>get_char_symb_from_vartype(this.rsmspectr.klass_T_offspos));
		cflex_head_size += Byte.BYTES;				
		
		
		// Padding by 0's
		for(long i = cflex_head_size;
				i < this.flex_header_byte_size;i ++) { 
			fw.writeByte(0x00);
		}
		
	}
	
	
	public long get_spectra_header_bsize()
			throws IllegalArgumentException {
			
		return 
				+ VariableType1.<T_numMTs>get_bsize_vartype(this.rsmspectr.klass_T_numMTs)
				+ this.rsmspectr.mtimes.size() * VariableType1.<T_mtime>get_bsize_vartype(this.rsmspectr.klass_T_mtime)
				+ this.rsmspectr.mtimes.size() * VariableType1.<T_offspos>get_bsize_vartype(this.rsmspectr.klass_T_offspos) * 2
				+ this.rsmspectr.mtimes.size() * VariableType1.<T_msdat_bsize>get_bsize_vartype(this.rsmspectr.klass_T_msdat_bsize) * 2;		

	}
	

	
	public void write_spectra_header_to_file(DataOutputStream fw)
			throws IOException{
		
		long header_bytes = this.flex_header_byte_size + this.get_spectra_header_bsize();
		
		if(Integer.class.equals(rsmspectr.klass_T_numMTs)){
			fw.writeInt(this.rsmspectr.mtimes.size());
		} else {
			fw.writeLong((long)this.rsmspectr.mtimes.size());
		}
		
		
		for(T_mtime mt : this.rsmspectr.mtimes) {
			VariableType1.write_single_binary_val_to_file(fw, mt);
		}
		
		for(long relpos : this.relposs_mzs_starts()) {
			if(Integer.class.equals(this.rsmspectr.klass_T_offspos)) {
				fw.writeInt((int)(relpos + header_bytes));
			} else {
				fw.writeLong(relpos + header_bytes);
			} 
		}
		
		for(long csize : this.sizes_mzs()) {
			if(Integer.class.equals(this.rsmspectr.klass_T_msdat_bsize)) {
				// .isInstance(csize) does not work
				fw.writeInt((int)(csize));
			} else {
				fw.writeLong((long)csize);
			} 			
			
		}
		
		for(long relpos : this.relposs_intsts_starts()) {
			if(Integer.class.equals(this.rsmspectr.klass_T_offspos)) {
				fw.writeInt((int)(relpos + header_bytes));
			} else {
				fw.writeLong(relpos + header_bytes);
			}
			
		}
		
		for(long csize : this.sizes_intsts()) {
			if(Integer.class.equals(this.rsmspectr.klass_T_msdat_bsize)) {
				// .isInstance(csize) does not work
				fw.writeInt((int)(csize));
			} else {
				fw.writeLong((long)csize);
			} 	
		}
		
	}
	
	
	public void output_to_file(Path opath) throws IOException {
		
		Files.createDirectories(opath.getParent());
		
		DataOutputStream fw = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(opath.toString())));
		
		this.write_flex_header_to_file(fw);
		this.write_spectra_header_to_file(fw);
		
		for(MassSpecrum_simple1_4<T_mz, T_intst>mspec : this.rsmspectr.mspecs) {
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


	public ArrayList<Long> sizes_ms()
			throws IllegalArgumentException {
		
		ArrayList<Long> osizes = new ArrayList<Long>();
		
		for(MassSpecrum_simple1_4<T_mz, T_intst> ms : this.rsmspectr.mspecs) {
			osizes.add(ms.bytesize_ms());
		}
		
		return(osizes);
	}
		
	public ArrayList<Long> sizes_mzs()
			throws IllegalArgumentException {
		
		ArrayList<Long> osizes = new ArrayList<Long>();
		
		for(MassSpecrum_simple1_4<T_mz, T_intst> ms : this.rsmspectr.mspecs) {
			osizes.add(ms.bytesize_mzs());	
		}
		
		return(osizes);
	}
	
	public ArrayList<Long> sizes_intsts()
			throws IllegalArgumentException {
		
		ArrayList<Long> osizes = new ArrayList<Long>();
		
		for(MassSpecrum_simple1_4<T_mz, T_intst> ms : this.rsmspectr.mspecs) {
			osizes.add(ms.bytesize_intsts());			
		}
		
		return(osizes);
		
	}	

	
}