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
	
	ArrayList<Float> get_nonredu_mzs(){
		
		ArrayList<Float> omzs = new ArrayList<Float>(this.key_mzs);
		Collections.sort(omzs);
		return(omzs);
		
	}

}
