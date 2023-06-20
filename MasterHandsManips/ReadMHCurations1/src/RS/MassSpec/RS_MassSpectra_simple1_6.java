package RS.MassSpec;

import java.util.ArrayList;

public class RS_MassSpectra_simple1_6 <
	T_mtime extends Number, T_mz extends Number, T_intst extends Number,
	T_numMTs extends Number, T_msdat_bsize extends Number, T_offspos extends Number>{

	public ArrayList<T_mtime> mtimes;
	public ArrayList<MassSpecrum_simple1_3<T_mz, T_intst, T_msdat_bsize>> mspecs;
	
	public T_mtime example_mtime;
	public T_mz example_mz;
	public T_intst example_intst;
	public T_numMTs example_nummts;
	public T_msdat_bsize example_msdat_bsize;
	public T_offspos example_offspos;
	
	public RS_MassSpectra_simple1_6(
			T_mtime iexample_mtime, T_mz iexample_mz,
			T_intst iexample_intst, T_numMTs iexample_nummts,
			T_msdat_bsize iexample_msdat_bsize, T_offspos iexample_offspos) {
		
		this.mtimes   = new ArrayList<T_mtime>();
		this.mspecs   = new ArrayList<MassSpecrum_simple1_3<T_mz, T_intst, T_msdat_bsize>>();
		
		this.example_mtime = iexample_mtime;
		this.example_mz = iexample_mz;
		this.example_intst = iexample_intst;
		this.example_nummts = iexample_nummts;
		this.example_msdat_bsize = iexample_msdat_bsize;
		this.example_offspos = iexample_offspos;
		
	}	
	
	public void add_ms(T_mtime imt, MassSpecrum_simple1_3<T_mz, T_intst, T_msdat_bsize> ms){
		
		ms.set_example_instances(this.example_mz, this.example_intst);
		
		this.mtimes.add(imt);
		this.mspecs.add(ms);

	}
	
	public void add_ms(T_mtime imt, T_mz[] mzs, T_intst[] intsts){
		
		this.mtimes.add(imt);
		
		MassSpecrum_simple1_3<T_mz, T_intst, T_msdat_bsize> ms = new MassSpecrum_simple1_3<T_mz, T_intst, T_msdat_bsize>();
		ms.mzs    = mzs;
		ms.intsts = intsts;
		
		ms.set_example_instances(this.example_mz, this.example_intst);
		
		this.mspecs.add(ms);

	}

}
