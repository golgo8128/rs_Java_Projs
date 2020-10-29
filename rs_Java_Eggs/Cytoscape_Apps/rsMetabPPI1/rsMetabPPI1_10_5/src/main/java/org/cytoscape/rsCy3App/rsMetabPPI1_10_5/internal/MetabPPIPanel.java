package org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal.rs_Java_Proj2_src_copy.general.fileproc.FileArrayProvider1;
import org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal.rs_Java_Proj2_src_copy.general.strproc.LineSplit1;
import org.cytoscape.rsCy3App.rsMetabPPI1_10_5.internal.rs_Java_Proj2_src_copy.general.strproc.StrJoin1;
import org.cytoscape.work.TaskIterator;

public class MetabPPIPanel extends JPanel implements CytoPanelComponent, ActionListener {

	public static final String NETWORKNAME       = "MetabPPI v1.10.5"; // new String("MetabPPI v1.10.2")
	public static final String READFILEBUTTONSTR = "Read from file";
	public static final String EXECBUTTONSTR     = "Generate subnet.";

	
	private final RegServiceBag1_3 rSB;
	
	private JLabel readfilelabel;
	private JTextArea area1;
	private JButton runbutton;
	
	private static final long serialVersionUID = 7381145900825146590L;

	public MetabPPIPanel(RegServiceBag1_3 regServiceBag) {

		this.rSB = regServiceBag;
		
		// setBounds(100, 100, 150, 250);
		// setSize(100,150);
		setPreferredSize(new Dimension(300, 100));
			
		// Outer panel
		JPanel outerpanel = new JPanel();
		outerpanel.setLayout(new BoxLayout(outerpanel, BoxLayout.Y_AXIS));

		this.add(outerpanel);
		this.setVisible(true);
		
		// Upper section panel
		JPanel uppersectionpanel = new JPanel();
		uppersectionpanel.setLayout(new BoxLayout(uppersectionpanel, BoxLayout.X_AXIS));
		outerpanel.add(uppersectionpanel);
		
		// Components on upper section panel
	    JButton fileselbutton = new JButton(READFILEBUTTONSTR);
	    fileselbutton.addActionListener(this);
	    uppersectionpanel.add(fileselbutton);

		readfilelabel = new JLabel();
		readfilelabel.setText("(File not selected)");
		uppersectionpanel.add(readfilelabel);		
		
		// Text area on outer panel
		area1 = new JTextArea(null, 20, 15);
		area1.setText("Input HMDB numbers");
		JScrollPane scrollpane = new JScrollPane(area1);
		outerpanel.add(scrollpane);
	
		// Execution button at the bottom of outer panel
	    runbutton = new JButton(EXECBUTTONSTR);
	    runbutton.addActionListener(this);   
		outerpanel.add(runbutton);

	}	

	public void actionPerformed(ActionEvent e){

		if (e.getActionCommand().equals(READFILEBUTTONSTR)){
			JFileChooser filechooser = new JFileChooser();

			int selected = filechooser.showOpenDialog(this);
			if (selected == JFileChooser.APPROVE_OPTION){
				File file = filechooser.getSelectedFile();
				readfilelabel.setText(file.getName());
	
		        FileArrayProvider1 fap = new FileArrayProvider1(file.getPath());
		        try {
		        	String[] lines = fap.readLines("strip");
		        	StrJoin1 linesJoin = new StrJoin1(lines);
		        	area1.setText(linesJoin.strjoin("\n"));

		        } catch(IOException ioe){
		        	System.err.println("File read error.");
		        }				

			}
		}
		
		else if (e.getActionCommand().equals(EXECBUTTONSTR)){

			HashMap<String, Object> task_share_info_h = new HashMap<String, Object>();
			
			String itext = area1.getText();
			LineSplit1 lsplit = new LineSplit1(itext);
			String[] lines = lsplit.linesplit("strip");
			
			Generate_sub_MetabPPI1_3 gen_sub_metabppi
				= new Generate_sub_MetabPPI1_3(rSB, task_share_info_h);
			gen_sub_metabppi.do_it(lines);
			
		}
		
		else {
			String itext = area1.getText();
			LineSplit1 lsplit = new LineSplit1(itext);
			String[] strs = lsplit.linesplit("strip");
			for(String str: strs){
				System.out.println(":" + str + ":");
			}
		}
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
		return "rs" + MetabPPIPanel.NETWORKNAME;
	}

}
