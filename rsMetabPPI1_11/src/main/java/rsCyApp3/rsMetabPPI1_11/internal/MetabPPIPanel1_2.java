package rsCyApp3.rsMetabPPI1_11.internal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;

import rsCyApp3.rsMetabPPI1_11.internal.rs_Java_Proj2_src_copy.general.fileproc.FileArrayProvider1;
import rsCyApp3.rsMetabPPI1_11.internal.rs_Java_Proj2_src_copy.general.strproc.LineSplit1;
import rsCyApp3.rsMetabPPI1_11.internal.rs_Java_Proj2_src_copy.general.strproc.StrJoin1;



public class MetabPPIPanel1_2 extends JPanel implements CytoPanelComponent, ActionListener, FocusListener {

	public static String NETWORKNAME;
	public static final String READFILEBUTTONSTR = "Read from file";
	public static final String EXECBUTTONSTR     = "Generate subnet.";

	public static final String initial_message
		= "Input HMDB numbers.\n" +
		  "Each line should contain single ID.\n" +
		  "\n" +
		  "Example:\n" +
		  "HMDB00243\n" +
		  "HMDB00094\n" +
		  "HMDB00223\n";
	
	
	private final RegServiceBag1_4 rSB;
	
	private JLabel readfilelabel;
	private JTextArea tarea1;
	private JButton runbutton;
	
	private boolean t_area_focused_flag = false;
	
	private static final long serialVersionUID = 7381145900825146590L;

	public MetabPPIPanel1_2(RegServiceBag1_4 regServiceBag, String prognamever) {

		this.rSB = regServiceBag;
		MetabPPIPanel1_2.NETWORKNAME = "MetabPPI " + prognamever;
		
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
		tarea1 = new JTextArea(null, 20, 15);
		tarea1.addFocusListener(this);
		tarea1.setText("### rsMetabPPI " + prognamever + " ###\n\n" +
				MetabPPIPanel1_2.initial_message);
		JScrollPane scrollpane = new JScrollPane(tarea1);
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
		        	tarea1.setText(linesJoin.strjoin("\n"));

		        } catch(IOException ioe){
		        	System.err.println("File read error.");
		        }				

			}
		}
		
		else if (e.getActionCommand().equals(EXECBUTTONSTR)){

			HashMap<String, Object> task_share_info_h = new HashMap<String, Object>();
			runbutton.setEnabled(false);
			task_share_info_h.put("runbutton", runbutton);
			
			String itext = tarea1.getText();
			LineSplit1 lsplit = new LineSplit1(itext);
			String[] lines = lsplit.linesplit("strip");
			
			Task gen_sub_metabppi_task
				= new Generate_sub_MetabPPI_task1_4(rSB, task_share_info_h, lines);
			rSB.taskManager.execute(new TaskIterator(gen_sub_metabppi_task));
			
		}
		
		else {
			String itext = tarea1.getText();
			LineSplit1 lsplit = new LineSplit1(itext);
			String[] strs = lsplit.linesplit("strip");
			for(String str: strs){
				System.out.println(":" + str + ":");
			}
		}
	}	  	
	
	public void focusGained(FocusEvent e) {
		if(!t_area_focused_flag){
			tarea1.setText("");
			t_area_focused_flag = true;
		}
				
    }

	public void focusLost(FocusEvent e) {

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
		return "rsMetabPPI"; // + MetabPPIPanel1_2.NETWORKNAME;
	}

}
