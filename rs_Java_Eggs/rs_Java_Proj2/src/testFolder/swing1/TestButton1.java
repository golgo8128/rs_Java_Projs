package testFolder.swing1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestButton1 extends JFrame implements ActionListener {

	TestButton1() {
		getContentPane().setLayout(new FlowLayout());

		JButton b1 = new JButton("OK");
		b1.addActionListener(this);
		getContentPane().add(b1);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("JButtonTest");
		setSize(200, 100);
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("OK");
	}

	public static void main(String[] args) {
		new TestButton1();
	}	
	
}
