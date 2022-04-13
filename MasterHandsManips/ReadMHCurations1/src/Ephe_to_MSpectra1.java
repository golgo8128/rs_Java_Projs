import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import emon2.api.ElectropherogramInfo;
import emon2.api.MasterHands;
import emon2.api.PeakInfo;
import emon2.api.SessionInfo;

public class Ephe_to_MSpectra1 {

	public HashMap<Float, HashMap<Float, Integer>> mt_mz_to_intsty;
	public Set<Float> key_mzs;
	private SessionInfo sess;
	
	Ephe_to_MSpectra1(MasterHands imh, SessionInfo isess){
		
		this.sess = isess;
		this.key_mzs = new HashSet<Float>();
		this.mt_mz_to_intsty
			= new HashMap<Float, HashMap<Float, Integer>>();
		
		
		for(PeakInfo pk : sess.getPeakGroupList()) {
			
			ElectropherogramInfo ephe =
					imh.getElectropherogram(sess.getId(), pk.getId());

			float mz = ephe.getMz();
			List<Float>   mts    = ephe.getMtList();
			List<Integer> intsts = ephe.getIntensityList();
			
			this.key_mzs.add(mz);
			
			for(Float mt: mts) {
				this.mt_mz_to_intsty.putIfAbsent(mt, new HashMap<Float, Integer>());
				for(Integer intsty : intsts) {
					this.mt_mz_to_intsty.get(mt).put(mz, intsty);
				}
			}

		}		
		
	}
	
	public RS_MassSpectra_simple1_2 <Float, Float, Integer>
		to_RS_MSS(){
		
		RS_MassSpectra_simple1_2<Float, Float, Integer> mss
			= new RS_MassSpectra_simple1_2<Float, Float, Integer>();
		
		ArrayList<Float> mts = 
				new ArrayList<Float>(this.mt_mz_to_intsty.keySet());
		Collections.sort(mts);
		ArrayList<Float> mzs_all = this.get_nonredu_mzs();
		
		for(Float mt: mts) {

			ArrayList<Float> mzs_avail = new ArrayList<Float>();
			ArrayList<Integer> intsts_avail = new ArrayList<Integer>();
			
			HashMap<Float, Integer> mz_to_intsty
				= this.mt_mz_to_intsty.get(mt);
			for(Float mz: mzs_all) {
				if(mz_to_intsty.containsKey(mz)) {
					mzs_avail.add(mz);
					intsts_avail.add(mz_to_intsty.get(mz));
				}				
			}
			
			if(mzs_avail.size() > 0) {
			
				mss.add_ms(
						mt,
						mzs_avail.toArray(new Float[ mzs_avail.size() ]),
						intsts_avail.toArray(new Integer[ intsts_avail.size() ]));
				// public T[] toArray(T[] a) ... T is type, doesn't necessary mean generic.
				// returns reference to allocated memory if possible.
				
			}
			
		}
			
		return mss;
		
	}
	
	
	public ArrayList<Float> get_nonredu_mzs(){
		
		ArrayList<Float> omzs = new ArrayList<Float>(this.key_mzs);
		Collections.sort(omzs);
		return(omzs);
		
	}

}
