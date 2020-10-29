package rsCy3App.rsMetabPPI1_10_2.internal;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

public class rsCy3App_Usefuls1 {

	
	public static HashSet<String> getNetNames(RegServiceBag1_3 rSB){
		
		Set<CyNetwork> netSet = rSB.cyNetworkManager.getNetworkSet();
		HashSet<String> netNameSet = new HashSet<String>();
	
		for(CyNetwork net:netSet){
			netNameSet.add(net.getRow(net).get(CyNetwork.NAME, String.class));
		}
		
		return netNameSet;
		
	}	

	public static CyNetworkView take_first_view(RegServiceBag1_3 rSB, CyNetwork net1){
	
		CyNetworkView[] netviews
			= (CyNetworkView[])rSB.networkViewManager.getNetworkViews(net1).toArray(new CyNetworkView[0]);
		if(netviews.length > 0){
			return netviews[0]; // Just take the first view.
		}
		else {
			return null;
		}
	}
}
