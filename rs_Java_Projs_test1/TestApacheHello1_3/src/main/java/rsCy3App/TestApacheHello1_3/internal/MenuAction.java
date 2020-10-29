package rsCy3App.TestApacheHello1_3.internal;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	public MenuAction(CyApplicationManager cyApplicationManager, final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps");
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		
		String[] strarray = { "Hello", "Cytoscape", "World \\(^_^)/" };
		JOptionPane.showMessageDialog(null, StringUtils.join(strarray, " "));
		
	}
}
