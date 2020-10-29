package rsCy3App.SubgraphCreateTest5_2.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.TaskManager;


public class MenuActionSubgCreation extends AbstractCyAction {
	
	private final TaskManager<?,?> taskManager;
	private final SubgCreationTaskFactory taskFactory;
	
	public MenuActionSubgCreation(CyApplicationManager cyApplicationManager,
			TaskManager<?,?> taskManager,
			SubgCreationTaskFactory taskFactory,
			final String menuTitle) {		
		
		super(menuTitle, cyApplicationManager, null, null);
		// Menu setting
		setPreferredMenu("Apps");
		this.setName(menuTitle);

		this.taskManager = taskManager;	
		this.taskFactory = taskFactory;
		
	}

	@Override	
	public void actionPerformed(ActionEvent e) {
		
		// Executes the task.
		taskManager.execute(taskFactory.createTaskIterator());

	}	

}
