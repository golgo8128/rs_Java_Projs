package testFolder.swingFileButtonTextArea1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// Based on http://www.javadrive.jp/tutorial/jtextarea/index2.html
// http://www.javadrive.jp/tutorial/jframe/index6.html

public class BuTAreaTest2 extends JFrame implements ActionListener {

	JLabel label;

	public static void main(String args[]){
		BuTAreaTest2 frame = new BuTAreaTest2("rsMetabPPI");
		frame.setVisible(true);
	}

	BuTAreaTest2(String title){
		setTitle(title);
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JButton button = new JButton("Read from file");
	    button.addActionListener(this);
		
		JPanel p = new JPanel();
		p.add(button);

		JTextArea area1 = new JTextArea("Input HMDB numbers");
		// area1.setText("Good moooorning----!!");
		p.add(area1);

		label = new JLabel();
		p.add(label);
		
		Container contentPane = getContentPane();
		contentPane.add(p, BorderLayout.CENTER);
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
	  

	

