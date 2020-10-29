package rsCy3App.SubgraphCreateTest5_3.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.TaskManager;

public class MenuActionSubgCreation extends AbstractCyAction {
	
	private final CyApplicationManager cyApplicationManager;
	private final TaskManager<?,?> taskManager;
	private final NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory;
	
	public MenuActionSubgCreation(CyApplicationManager cyApplicationManager,
			TaskManager<?,?> taskManager,
			NewNetworkSelectedNodesOnlyTaskFactory newNetfromSelNodesTFactory,
			final String menuTitle) {		
		
		super(menuTitle, cyApplicationManager, null, null);
		// Menu setting
		setPreferredMenu("Apps");
		this.setName(menuTitle);

		this.cyApplicationManager = cyApplicationManager;
		this.taskManager = taskManager;	
		this.newNetfromSelNodesTFactory = newNetfromSelNodesTFactory;
		
	}

	@Override	
	public void actionPerformed(ActionEvent e) {
		
		// Executes the task.
		// Instance for original task.
		SubgCreationTaskFactory taskFactory = 
				new SubgCreationTaskFactory(cyApplicationManager,
						newNetfromSelNodesTFactory);		
		taskManager.execute(taskFactory.createTaskIterator());

	}	

}
