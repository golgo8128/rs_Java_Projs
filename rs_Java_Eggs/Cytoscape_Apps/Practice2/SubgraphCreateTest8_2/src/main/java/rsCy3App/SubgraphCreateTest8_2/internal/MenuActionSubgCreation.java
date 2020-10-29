package rsCy3App.SubgraphCreateTest8_2.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.TaskManager;

public class MenuActionSubgCreation extends AbstractCyAction {
	
	private final CyApplicationManager cyApplicationManager;
	private final CyNetworkManager cyNetworkManager;
	private final CyNetworkViewFactory networkViewFactory;
	private final CyNetworkViewManager networkViewManager;
	private final TaskManager<?,?> taskManager;
	private final NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory;
	private final SelectFirstNeighborsTaskFactory sel1stNeiTFactory;
	private final CyLayoutAlgorithmManager cyLayoutManager;
	private final VisualMappingManager vmmServiceRef;
	
	public MenuActionSubgCreation(CyApplicationManager cyApplicationManager,
			CyNetworkManager cyNetworkManager,
			CyNetworkViewFactory networkViewFactory,
			CyNetworkViewManager networkViewManager,
			TaskManager<?,?> taskManager,
			NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory,
			SelectFirstNeighborsTaskFactory sel1stNeiTFactory,
			CyLayoutAlgorithmManager cyLayoutManager,
			VisualMappingManager vmmServiceRef,
			final String menuTitle) {		
		
		super(menuTitle, cyApplicationManager, null, null);
		// Menu setting
		setPreferredMenu("Apps");
		this.setName(menuTitle);

		this.cyApplicationManager = cyApplicationManager;
		this.cyNetworkManager = cyNetworkManager;
		this.networkViewFactory = networkViewFactory;
		this.networkViewManager = networkViewManager;
		this.taskManager = taskManager;	
		this.newNetfromSelNodesTFactory = newNetfromSelNodesTFactory;
		this.sel1stNeiTFactory = sel1stNeiTFactory;
		this.cyLayoutManager = cyLayoutManager;
		this.vmmServiceRef = vmmServiceRef;
		
	}

	@Override	
	public void actionPerformed(ActionEvent e) {
		
		// Executes the task.
		// Instance for original task.
		SubgCreationTaskFactory taskFactory = 
				new SubgCreationTaskFactory(cyApplicationManager,
						cyNetworkManager, networkViewFactory, networkViewManager,
						newNetfromSelNodesTFactory, sel1stNeiTFactory,
						cyLayoutManager, vmmServiceRef);		
		taskManager.execute(taskFactory.createTaskIterator());

	}	

}
