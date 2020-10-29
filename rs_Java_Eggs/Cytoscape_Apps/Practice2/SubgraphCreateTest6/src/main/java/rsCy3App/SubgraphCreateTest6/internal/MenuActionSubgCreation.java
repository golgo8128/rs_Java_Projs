package rsCy3App.SubgraphCreateTest6.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.work.TaskManager;

public class MenuActionSubgCreation extends AbstractCyAction {
	
	private final CyApplicationManager cyApplicationManager;
	private final TaskManager<?,?> taskManager;
	private final NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory;
	private final SelectFirstNeighborsTaskFactory sel1stNeiTFactory;
	
	public MenuActionSubgCreation(CyApplicationManager cyApplicationManager,
			TaskManager<?,?> taskManager,
			NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory,
			SelectFirstNeighborsTaskFactory sel1stNeiTFactory,
			final String menuTitle) {		
		
		super(menuTitle, cyApplicationManager, null, null);
		// Menu setting
		setPreferredMenu("Apps");
		this.setName(menuTitle);

		this.cyApplicationManager = cyApplicationManager;
		this.taskManager = taskManager;	
		this.newNetfromSelNodesTFactory = newNetfromSelNodesTFactory;
		this.sel1stNeiTFactory = sel1stNeiTFactory;
		
	}

	@Override	
	public void actionPerformed(ActionEvent e) {
		
		// Executes the task.
		// Instance for original task.
		SubgCreationTaskFactory taskFactory = 
				new SubgCreationTaskFactory(cyApplicationManager,
						newNetfromSelNodesTFactory, sel1stNeiTFactory);		
		taskManager.execute(taskFactory.createTaskIterator());

	}	

}
