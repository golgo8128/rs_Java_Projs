package rsCy3App.rsMetabPPI1_2.internal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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

public class MetabPPIPanel extends JPanel implements CytoPanelComponent, ActionListener {

	public static final String READFILEBUTTONSTR = "Read from file";
	
	private JLabel readfilelabel;
	private JTextArea area1;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7381145900825146590L;

	public MetabPPIPanel() {

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
	    JButton runbutton = new JButton("Run!");
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
		return "rsMetabPPI";
	}

}
