package rsCy3App.SubgraphCreateTest4.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskManager;

public class MenuActionSubgCreation extends AbstractCyAction {

	private final CyApplicationManager cyApplicationManager;
	private final NewNetworkSelectedNodesOnlyTaskFactory fromSelection;	
	private final TaskManager<?,?> taskManager;
	private final SubgCreationTaskFactory taskFactory;
	
	public MenuActionSubgCreation(CyApplicationManager cyApplicationManager,
			TaskManager<?,?> taskManager,
			SubgCreationTaskFactory taskFactory,
			NewNetworkSelectedNodesOnlyTaskFactory fromSelection,
			final String menuTitle) {		
		
		super(menuTitle, cyApplicationManager, null, null);
		// Menu setting
		setPreferredMenu("Apps");
		this.setName(menuTitle);

		this.cyApplicationManager = cyApplicationManager;
		this.taskManager = taskManager;	
		this.taskFactory = taskFactory;
		this.fromSelection = fromSelection;
		
	}

	@Override	
	public void actionPerformed(ActionEvent e) {
		
		// Executes the task.
		taskManager.execute(taskFactory.createTaskIterator(cyApplicationManager, fromSelection));

	}	

}
