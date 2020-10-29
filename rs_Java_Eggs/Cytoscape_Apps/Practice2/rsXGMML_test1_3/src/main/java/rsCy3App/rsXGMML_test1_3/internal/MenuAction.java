package rsCy3App.rsXGMML_test1_3.internal;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5828723789347793478L;

	public MenuAction(CyApplicationManager cyApplicationManager,
			LoadNetworkFileTaskFactory loadNetworkFileTaskFactory,
			CyLayoutAlgorithmManager cyLayoutManager, 
			final String menuTitle){
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps");
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		JOptionPane.showMessageDialog(null, "Hello Cytoscape World!");
		
	}
}
