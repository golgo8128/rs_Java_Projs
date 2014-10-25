package rsCy3App.rsMetabPPI1_12_2.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.distribution.HypergeometricDistribution;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTable;

import rsCy3App.rsMetabPPI1_12_2.internal.rs_Java_Proj4_cp.calculation.stats.PValsCorrect1;

public class AddHygecdf_pvals1 {

	private RegServiceBag1_4 rSB;
	private CyNetwork netall;
	private CyNetwork netsub;
	private int num_enz_netall = 0;
	private int num_enz_netsub = 0;
	
	public AddHygecdf_pvals1(RegServiceBag1_4 regServiceBag, 
			CyNetwork netall, CyNetwork netsub){	
	
		this.rSB    = regServiceBag;
		this.netall = netall;
		this.netsub = netsub;
		
		CyTable nodetable_netall = netall.getDefaultNodeTable();
		CyTable nodetable_netsub = netsub.getDefaultNodeTable();	
		
		for(CyNode node_netall: netall.getNodeList()){
			String nodetype = nodetable_netall.getRow(node_netall.getSUID()).get("Node type", String.class);
			if(nodetype.equals("Enzyme")){
				num_enz_netall ++;
			}
		}
		
		for(CyNode node_netsub: netsub.getNodeList()){
			String nodetype = nodetable_netsub.getRow(node_netsub.getSUID()).get("Node type", String.class);
			if(nodetype.equals("Enzyme")){
				num_enz_netsub ++;
			}
		}	
		
	}
	
	public void write_hygecdf_pvals(){
		
		CyTable nodetable_netsub = netsub.getDefaultNodeTable();	
		CyTable edgetable_netsub = netsub.getDefaultEdgeTable();
		
		nodetable_netsub.createColumn("Prot2Enz p-val", Double.class, true);
		nodetable_netsub.createColumn("Prot2Enz BH p-val", Double.class, true);
		nodetable_netsub.createColumn("-log10 Prot2Enz BH p-val", Double.class, true);				
		
		List<CyNode> netsub_protlist  = new ArrayList<CyNode>();
		List<Double> netsub_protpvals = new ArrayList<Double>();
		
		for(CyNode inode: netsub.getNodeList()){
			String nodetype = nodetable_netsub.getRow(inode.getSUID()).get("Node type", String.class);
			// String nodename = nodetable_netsub.getRow(inode.getSUID()).get(CyNetwork.NAME, String.class);
			// System.out.printf("%s: %s\n", nodename, nodetype);
			if(nodetype.equals("Protein")){
				netsub_protlist.add(inode);
				double pval = calc_hygecdf_pval(inode);
				netsub_protpvals.add(pval);
			}
		}		

		Double[] netsub_protpvals_tmp1 = netsub_protpvals.toArray(new Double[0]);
		double[] netsub_protpvals_arr = new double[netsub_protpvals_tmp1.length];
		for(int i = 0;i < netsub_protpvals_tmp1.length;i ++){
			netsub_protpvals_arr[i] = (double)netsub_protpvals_tmp1[i];
		}
		
		PValsCorrect1 bhp1 = new PValsCorrect1(netsub_protpvals_arr);
		double[] bh_pvalues1 = bhp1.calc_BH_pvals();
		
		for(int i = 0;i < netsub_protpvals_arr.length;i ++){
			CyNode prot    = netsub_protlist.get(i);
			double pval    = netsub_protpvals_arr[i];
			double pval_bh = bh_pvalues1[i];
			nodetable_netsub.getRow(prot.getSUID()).set("Prot2Enz p-val", pval);
			nodetable_netsub.getRow(prot.getSUID()).set("Prot2Enz BH p-val", pval_bh);
			nodetable_netsub.getRow(prot.getSUID()).set("-log10 Prot2Enz BH p-val", -Math.log10(pval_bh));
			// System.out.printf("%s: %f\n", prot.toString(), pval);
		}

		for(int i = 0;i < netsub_protpvals_arr.length;i ++){		
			CyNode prot    = netsub_protlist.get(i);
			double pval_bh = bh_pvalues1[i];
			if(pval_bh < 0.05){
				for(CyEdge ppi: netsub.getAdjacentEdgeIterable(prot, CyEdge.Type.ANY)){
					edgetable_netsub.getRow(ppi.getSUID()).set("interaction", "PPI (significant)");
				}
			}
		}
		
	}
	
	public double calc_hygecdf_pval(CyNode protnode_netsub){
		
		HashMap<String,CyNode> nodename2cynode_netall_h = rsCy3App_Usefuls1.get_nodename_to_cynode_h(netall);		
		HashMap<String,CyNode> nodename2cynode_netsub_h = rsCy3App_Usefuls1.get_nodename_to_cynode_h(netsub);		
		
		CyTable nodetable_netall = netall.getDefaultNodeTable();
		CyTable nodetable_netsub = netsub.getDefaultNodeTable();		
		
		String protnodename = 
				nodetable_netsub.getRow(protnode_netsub.getSUID()).get(CyNetwork.NAME, String.class);
		
		CyNode protnode_netall = nodename2cynode_netall_h.get(protnodename);
				
		List<CyNode>neighb_netall = netall.getNeighborList(protnode_netall, CyEdge.Type.ANY);
		List<CyNode>neighb_netsub = netsub.getNeighborList(protnode_netsub, CyEdge.Type.ANY);
		
		int num_enz_target_netall = 0;
		int num_enz_target_netsub = 0;
		
		for(CyNode node_netall: neighb_netall){

			String nodename = nodetable_netall.getRow(node_netall.getSUID()).get(CyNetwork.NAME, String.class);			
			String nodetype = nodetable_netall.getRow(node_netall.getSUID()).get("Node type", String.class);
			
			if(nodetype.equals("Enzyme")){
				num_enz_target_netall ++;
				
				CyNode cynode_in_netsub = nodename2cynode_netsub_h.get(nodename);
				if(neighb_netsub.contains(cynode_in_netsub)){
					num_enz_target_netsub += 1;
				}
			}
		}
		
		System.out.printf("%s: %d %d %d %d\n", protnodename, 
						  num_enz_netall, num_enz_target_netall,
						  num_enz_netsub, num_enz_target_netsub);
		
		HypergeometricDistribution hyged =
				new HypergeometricDistribution(num_enz_netall,
						num_enz_target_netall, num_enz_netsub);
		
		return(hyged.upperCumulativeProbability(num_enz_target_netsub));
		
	}
	
}
