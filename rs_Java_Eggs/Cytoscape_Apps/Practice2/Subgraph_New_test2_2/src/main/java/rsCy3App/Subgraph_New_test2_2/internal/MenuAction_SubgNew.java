package rsCy3App.Subgraph_New_test2_2.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskManager;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction_SubgNew extends AbstractCyAction {

	private final CyNetworkManager cyNetworkManager;	
	private final CyApplicationManager cyApplicationManager;
	private final NewNetworkSelectedNodesOnlyTaskFactory fromSelection;	
	private final TaskManager<?,?> taskManager;
	
	public MenuAction_SubgNew(CyApplicationManager cyApplicationManager,
							  CyNetworkManager cyNetworkManager,
							  TaskManager<?,?> taskManager,
							  NewNetworkSelectedNodesOnlyTaskFactory fromSelection,
							  final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		this.taskManager = taskManager;
		setPreferredMenu("Apps");
		this.setName(menuTitle);
		
		this.cyApplicationManager = cyApplicationManager;
		this.cyNetworkManager = cyNetworkManager;
		this.fromSelection = fromSelection;
		
	}

	public void actionPerformed(ActionEvent e) {

		CyNetwork net1 = cyApplicationManager.getCurrentNetwork();		
		
		String[] targetNodeNames = {"Node F", "Node G", "Node H"};
		
		for (final CyNode node : net1.getNodeList()) {
			for(final String targetNodeName : targetNodeNames){
				if(net1.getRow(node).get(CyNetwork.NAME, String.class).equals(targetNodeName)){
					net1.getRow(node).set(CyNetwork.SELECTED, true);
				}

			}
		}
		
		cyApplicationManager.getCurrentNetworkView().updateView();
		Subg_Create_Task1 sctask = new Subg_Create_Task1(net1, fromSelection);
		taskManager.execute(new TaskIterator(sctask));

		
	}
		
}
