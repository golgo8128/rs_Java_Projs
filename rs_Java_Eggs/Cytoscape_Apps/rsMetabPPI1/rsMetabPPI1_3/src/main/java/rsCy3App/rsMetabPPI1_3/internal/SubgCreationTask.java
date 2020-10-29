package rsCy3App.rsMetabPPI1_3.internal;

import java.util.Collection;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;

public class SubgCreationTask extends AbstractTask {

	private final RegServiceBag1 rSB;
	private final String[] targetNodeNames;
	
	public SubgCreationTask(RegServiceBag1 regServiceBag, String[] targetNodeNames){
		
		this.rSB = regServiceBag;
		this.targetNodeNames = targetNodeNames;
	}

	public void run(final TaskMonitor taskMonitor) throws Exception {

		CyNetwork net1 = rSB.cyApplicationManager.getCurrentNetwork();		
		VisualStyle vs = rSB.vmmServiceRef.getCurrentVisualStyle();
				
		for (final CyNode node : net1.getNodeList()) {
			for(final String targetNodeName : targetNodeNames){
				if(net1.getRow(node).get(CyNetwork.NAME, String.class).equals(targetNodeName)){
					net1.getRow(node).set(CyNetwork.SELECTED, true);
				}

			}
		}
		
		TaskIterator itrNei = rSB.sel1stNeiTFactory.createTaskIterator(net1);
		Task selectionTaskNei = itrNei.next();
		selectionTaskNei.run(taskMonitor);
		
		TaskIterator itrNn = rSB.newNetfromSelNodesTFactory.createTaskIterator(net1);
		Task selectionTaskNn = itrNn.next();
		selectionTaskNn.run(taskMonitor);
		
		// Make the view of the network
		Set<CyNetwork> netSet = rSB.cyNetworkManager.getNetworkSet();
		Object net_objs[] = netSet.toArray();
		CyNetwork net_last = (CyNetwork) net_objs[net_objs.length - 1];
		CyNetworkView netview = rSB.networkViewFactory.createNetworkView(net_last);
		rSB.networkViewManager.addNetworkView(netview);
		
		// Layout network
		CyLayoutAlgorithm cylayoutAlgorithm = rSB.cyLayoutManager.getLayout("force-directed");
		TaskIterator itrLay = cylayoutAlgorithm.createTaskIterator(netview,
				cylayoutAlgorithm.getDefaultLayoutContext(),
				CyLayoutAlgorithm.ALL_NODE_VIEWS, null);
		Task selectionTaskLay  = itrLay.next();
		selectionTaskLay.run(taskMonitor);
	
		vs.apply(netview);
		
		netview.updateView();
		
		Collection<CyLayoutAlgorithm> alllays = rSB.cyLayoutManager.getAllLayouts();
		for(CyLayoutAlgorithm cylay: alllays){
			System.out.println(cylay.getName());
		}
		
	}

}
