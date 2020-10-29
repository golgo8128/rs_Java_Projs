package rsCy3App.Subgraph_New_test3.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskManager;

public class MenuAction_SubgCreation extends AbstractCyAction {

	private final CyNetworkManager cyNetworkManager;	
	private final CyApplicationManager cyApplicationManager;
	private final NewNetworkSelectedNodesOnlyTaskFactory fromSelection;	
	private final TaskManager<?,?> taskManager;
	
	public MenuAction_SubgCreation(CyApplicationManager cyApplicationManager,
			CyNetworkManager cyNetworkManager,
			TaskManager<?,?> taskManager,
			NewNetworkSelectedNodesOnlyTaskFactory fromSelection,
			final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		// Menu setting
		setPreferredMenu("Apps");
		this.setName(menuTitle);

		this.cyApplicationManager = cyApplicationManager;
		this.cyNetworkManager = cyNetworkManager;
		this.taskManager = taskManager;	
		this.fromSelection = fromSelection;
		
	}

	@Override	
	public void actionPerformed(ActionEvent e) {
		
		// Executes the task.
		
		SubgCreationTask sctask = new SubgCreationTask(cyApplicationManager, fromSelection);
		taskManager.execute(new TaskIterator(sctask));
		// How can SubgCreationTaskFactory be useful?

	}	

}
