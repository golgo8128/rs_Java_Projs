package org.cytoscape.rsCy3App.Tab_Panel_Test1.internal;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JPanel;

import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;

public class MyCytoPanel extends JPanel implements CytoPanelComponent {

	CytoPanelComponent cytoPanelComponent;
	
	public MyCytoPanel(CytoPanelComponent cytoPanelComponent){
		this.cytoPanelComponent = cytoPanelComponent;		
	}
	
	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

}
