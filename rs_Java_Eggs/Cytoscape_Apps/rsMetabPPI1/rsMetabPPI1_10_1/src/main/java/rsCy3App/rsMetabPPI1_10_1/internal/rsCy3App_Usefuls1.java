package rsCy3App.rsMetabPPI1_10_1.internal;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyNetwork;

public class rsCy3App_Usefuls1 {

	
	public static HashSet<String> getNetNames(RegServiceBag1_2 rSB){
		
		Set<CyNetwork> netSet = rSB.cyNetworkManager.getNetworkSet();
		HashSet<String> netNameSet = new HashSet<String>();
	
		for(CyNetwork net:netSet){
			netNameSet.add(net.getRow(net).get(CyNetwork.NAME, String.class));
		}
		
		return netNameSet;
		
	}	
	
}
