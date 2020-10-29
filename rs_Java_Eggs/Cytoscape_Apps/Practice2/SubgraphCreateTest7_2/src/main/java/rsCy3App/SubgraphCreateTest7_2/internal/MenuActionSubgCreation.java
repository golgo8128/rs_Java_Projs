package rsCy3App.SubgraphCreateTest7_2.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.task.visualize.ApplyPreferredLayoutTaskFactory;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.TaskManager;

public class MenuActionSubgCreation extends AbstractCyAction {
	
	private final CyApplicationManager cyApplicationManager;
	private final CyNetworkManager cyNetworkManager;
	private final CyNetworkViewFactory networkViewFactory;
	private final CyNetworkViewManager networkViewManager;
	private final TaskManager<?,?> taskManager;
	private final NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory;
	private final SelectFirstNeighborsTaskFactory sel1stNeiTFactory;
	private final ApplyPreferredLayoutTaskFactory layoutTaskFactory;
	
	public MenuActionSubgCreation(CyApplicationManager cyApplicationManager,
			CyNetworkManager cyNetworkManager,
			CyNetworkViewFactory networkViewFactory,
			CyNetworkViewManager networkViewManager,
			TaskManager<?,?> taskManager,
			NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory,
			SelectFirstNeighborsTaskFactory sel1stNeiTFactory,
			ApplyPreferredLayoutTaskFactory layoutTaskFactory,
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
		this.layoutTaskFactory = layoutTaskFactory;
		
	}

	@Override	
	public void actionPerformed(ActionEvent e) {
		
		// Executes the task.
		// Instance for original task.
		SubgCreationTaskFactory taskFactory = 
				new SubgCreationTaskFactory(cyApplicationManager,
						cyNetworkManager, networkViewFactory, networkViewManager,
						newNetfromSelNodesTFactory, sel1stNeiTFactory,
						layoutTaskFactory);		
		taskManager.execute(taskFactory.createTaskIterator());

	}	

}
