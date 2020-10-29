package org.cytoscape.rsCy3App.Tab_Panel_Test2.internal;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;

public class MyCytoPanel extends JPanel implements CytoPanelComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7381145900825146590L;

	public MyCytoPanel() {

		JLabel lbXYZ = new JLabel("This is my Panel");

		this.add(lbXYZ);
		this.setVisible(true);
	}	
	
	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public CytoPanelName getCytoPanelName() {
		// TODO Auto-generated method stub
		return CytoPanelName.WEST;
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "MyPanel";
	}

}
