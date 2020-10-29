package testFolder.swingFileButtonTextArea1;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// Based on http://www.javadrive.jp/tutorial/jtextarea/index2.html
// http://www.javadrive.jp/tutorial/jframe/index6.html

public class BuTAreaTest1 extends JFrame {
	  public static void main(String args[]){
		  	BuTAreaTest1 frame = new BuTAreaTest1("Test Frame");
		    frame.setVisible(true);
		    }

	  BuTAreaTest1(String title){
		    setTitle(title);
		    setBounds(100, 100, 300, 250);
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    JPanel p = new JPanel();

		    JTextArea area1 = new JTextArea("Hello---");
		    area1.setText("Good moooorning----!!");
		    p.add(area1);

		    Container contentPane = getContentPane();
		    contentPane.add(p, BorderLayout.CENTER);
		  }
	  
}
	

