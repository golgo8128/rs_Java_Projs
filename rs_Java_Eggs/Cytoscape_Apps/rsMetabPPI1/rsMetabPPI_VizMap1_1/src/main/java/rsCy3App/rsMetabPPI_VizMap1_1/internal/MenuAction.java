package rsCy3App.rsMetabPPI_VizMap1_1.internal;

import java.awt.event.ActionEvent;

import org.cytoscape.application.swing.AbstractCyAction;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	private final RegServiceBag1_2 rSB;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuAction(RegServiceBag1_2 rSB, final String menuTitle) {
		
		super(menuTitle, rSB.cyApplicationManager, null, null);
		setPreferredMenu("Apps");
		
		this.rSB = rSB;
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		
		// Build_metabPPI1_1 bmPPI = new Build_metabPPI1_1(this.rSB);
		// bmPPI.build_net();
		
		Build_metabPPI_TaskFactory1 bmetabPPI_taskFactory = 
				new Build_metabPPI_TaskFactory1(rSB);		
		rSB.taskManager.execute(bmetabPPI_taskFactory.createTaskIterator());
		
		// VizMap1_1 vmap = new VizMap1_1(this.rSB);
		// vmap.apply_VStyle_I();
		
	}
}
