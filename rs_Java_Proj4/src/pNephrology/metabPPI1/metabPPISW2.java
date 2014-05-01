package pNephrology.metabPPI1;

import general.fileproc.FileArrayProvider1;
import general.strproc.LineSplit1;
import general.strproc.StrJoin1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// Based on http://www.javadrive.jp/tutorial/jtextarea/index2.html
// http://www.javadrive.jp/tutorial/jframe/index6.html

public class metabPPISW2 extends JFrame implements ActionListener {

	private JLabel readfilelabel;
	private JTextArea area1;
	
	public static void main(String args[]){
		metabPPISW2 frame = new metabPPISW2("rsMetabPPI");
		frame.setVisible(true);
	}

	metabPPISW2(String title){
		
		setTitle(title);
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Outer panel
		JPanel outerpanel = new JPanel();
		outerpanel.setLayout(new BoxLayout(outerpanel, BoxLayout.Y_AXIS));

		// Upper section panel
		JPanel uppersectionpanel = new JPanel();
		uppersectionpanel.setLayout(new BoxLayout(uppersectionpanel, BoxLayout.X_AXIS));
		outerpanel.add(uppersectionpanel);
		
		// Components on upper section panel
	    JButton fileselbutton = new JButton("Read from file");
	    fileselbutton.addActionListener(this);
	    uppersectionpanel.add(fileselbutton);

		readfilelabel = new JLabel();
		readfilelabel.setText("(File not selected)");
		uppersectionpanel.add(readfilelabel);		
		
		// Text area on outer panel
		area1 = new JTextArea("");
		area1.setText("Input HMDB numbers");
		JScrollPane scrollpane = new JScrollPane(area1);
		outerpanel.add(scrollpane);
	
		// Execution button at the bottom of outer panel
	    JButton runbutton = new JButton("Run!");
	    runbutton.addActionListener(this);   
		outerpanel.add(runbutton);
	    
		Container contentPane = getContentPane();
		contentPane.add(outerpanel, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e){

		if (e.getActionCommand().equals("Read from file")){
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

}
	  

	

