package testFolder.swing1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JFileChooserTest2 extends JFrame implements ActionListener {


	JLabel label;

	public static void main(String[] args){
		JFileChooserTest2 frame = new JFileChooserTest2();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 300, 200);
		frame.setTitle("ƒ^ƒCƒgƒ‹");
		frame.setVisible(true);
	}

	JFileChooserTest2(){
		JButton button = new JButton("file select");
		button.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(button);

		label = new JLabel();

		JPanel labelPanel = new JPanel();
		labelPanel.add(label);

		getContentPane().add(labelPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		JFileChooser filechooser = new JFileChooser();

		int selected = filechooser.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION){
			File file = filechooser.getSelectedFile();
			label.setText(file.getName());
		} else if (selected == JFileChooser.CANCEL_OPTION){
			label.setText("Cancelled");
		} else if (selected == JFileChooser.ERROR_OPTION){
			label.setText("Error or cancelled");
		}
	}
}

