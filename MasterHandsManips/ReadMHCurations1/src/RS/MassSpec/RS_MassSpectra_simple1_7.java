package RS.MassSpec;

import java.util.ArrayList;

public class RS_MassSpectra_simple1_7 <
	T_mtime extends Number, T_mz extends Number, T_intst extends Number,
	T_numMTs extends Number, T_msdat_bsize extends Number, T_offspos extends Number>{

	public ArrayList<T_mtime> mtimes;
	public ArrayList<MassSpecrum_simple1_4<T_mz, T_intst>> mspecs;
	
	public Class<T_mtime> klass_T_mtime;
	public Class<T_mz> klass_T_mz;
	public Class<T_intst> klass_T_intst;
	public Class<T_numMTs> klass_T_numMTs;
	public Class<T_msdat_bsize> klass_T_msdat_bsize;
	public Class<T_offspos> klass_T_offspos;
	
	public RS_MassSpectra_simple1_7(
			Class<T_mtime> iklass_T_mtime,
			Class<T_mz> iklass_T_mz,
			Class<T_intst> iklass_T_intst,
			Class<T_numMTs> iklass_T_numMTs,
			Class<T_msdat_bsize> iklass_T_msdat_bsize,
			Class<T_offspos> iklass_T_offspos) {
		
		this.mtimes   = new ArrayList<T_mtime>();
		this.mspecs   = new ArrayList<MassSpecrum_simple1_4<T_mz, T_intst>>();
		
		this.klass_T_mtime = iklass_T_mtime;
		this.klass_T_mz = iklass_T_mz;
		this.klass_T_intst = iklass_T_intst;
		this.klass_T_numMTs = iklass_T_numMTs;
		this.klass_T_msdat_bsize = iklass_T_msdat_bsize;
		this.klass_T_offspos = iklass_T_offspos;
		
	}	
	
	public void add_ms(T_mtime imt, MassSpecrum_simple1_4<T_mz, T_intst> ms){
		
		ms.set_Classes(this.klass_T_mz, this.klass_T_intst);
		
		this.mtimes.add(imt);
		this.mspecs.add(ms);

	}
	
	public void add_ms(T_mtime imt, T_mz[] mzs, T_intst[] intsts){
		
		this.mtimes.add(imt);
		
		MassSpecrum_simple1_4<T_mz, T_intst> ms = new MassSpecrum_simple1_4<T_mz, T_intst>();
		ms.mzs    = mzs;
		ms.intsts = intsts;
		
		ms.set_Classes(this.klass_T_mz, this.klass_T_intst);
		
		this.mspecs.add(ms);

	}

}
