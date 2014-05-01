package testFolder.swingFileButtonTextArea1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// Based on http://www.javadrive.jp/tutorial/jtextarea/index2.html
// http://www.javadrive.jp/tutorial/jframe/index6.html

public class BuTAreaTest3 extends JFrame implements ActionListener {

	JLabel label;

	public static void main(String args[]){
		BuTAreaTest3 frame = new BuTAreaTest3("rsMetabPPI");
		frame.setVisible(true);
	}

	BuTAreaTest3(String title){
		
		setTitle(title);
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel outerpanel = new JPanel();
		outerpanel.setLayout(new BoxLayout(outerpanel, BoxLayout.Y_AXIS));

		JPanel topsectionpanel = new JPanel();
		topsectionpanel.setLayout(new BoxLayout(topsectionpanel, BoxLayout.X_AXIS));
		outerpanel.add(topsectionpanel);
		
	    JButton button = new JButton("Read from file");
	    button.addActionListener(this);
	    topsectionpanel.add(button);

		label = new JLabel();
		label.setText("(File not selected)");
		topsectionpanel.add(label);		
		
		JTextArea area1 = new JTextArea("");
		area1.setText("Input HMDB numbers");
		outerpanel.add(area1);
	
		Container contentPane = getContentPane();
		contentPane.add(outerpanel, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e){
		JFileChooser filechooser = new JFileChooser();

		int selected = filechooser.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION){
			File file = filechooser.getSelectedFile();
			label.setText(file.getName());
		}
	}	  
}
	  

	

