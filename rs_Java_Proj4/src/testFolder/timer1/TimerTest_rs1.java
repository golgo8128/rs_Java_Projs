// This is from http://www.javadrive.jp/tutorial/timer/index1.html

package testFolder.timer1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerTest_rs1 extends JFrame implements ActionListener{

	Timer timer;
	JLabel label;
	int sec;

	public static void main(String[] args){
		TimerTest_rs1 frame = new TimerTest_rs1();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 150, 100);
		frame.setTitle("Anonymous Title");
		frame.setVisible(true);
	}

	TimerTest_rs1(){
		sec = 0;
		label = new JLabel();

		JPanel labelPanel = new JPanel();
        label.setOpaque(true);
		labelPanel.add(label);

		timer = new Timer(1000 , this);

		getContentPane().add(labelPanel, BorderLayout.CENTER);

		timer.start();
	}

	public void actionPerformed(ActionEvent e){
		label.setText(sec + " sec");
		if(sec % 2 == 0)
			label.setBackground(Color.YELLOW);
		else
			label.setBackground(Color.GRAY);
		
		if (sec >= 10){
			timer.stop();
		}else{
			sec++;
		}
	}
}
