// This is from http://www.javadrive.jp/tutorial/timer/index1.html

package testFolder.swing1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class SwingPoller1 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final SwingPoller1 instance = new SwingPoller1();
	
	private static Timer polltimer;
	private static JLabel indic_label;
	private static int secdbl_from_start;
	private static JButton quit_button;
		
	// private static RegServiceBag1_4 rSB = null;
	
	private SwingPoller1(){
		
		set_frame1();
		
		indic_label = new JLabel();
        indic_label.setOpaque(true);
        indic_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit_button = new JButton("Quit");
		quit_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		quit_button.addActionListener(this);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(Box.createRigidArea(new Dimension(1,10)));
		mainPanel.add(indic_label);
		mainPanel.add(Box.createRigidArea(new Dimension(1,10)));
		mainPanel.add(quit_button);
				
		polltimer = new Timer(500 , this);
		polltimer.setActionCommand("PollTimer");
		
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		secdbl_from_start = 0;
		
	}; 	
	
	/*
	public static SwingPoller1 getInstance(RegServiceBag1_4 rSB){

		SwingPoller1.rSB = rSB;
		return SwingPoller1.instance;
	}
	*/		
	
	public static SwingPoller1 getInstance(){
		
		/*
		 if(rSB == null){ 
			 throw new IllegalStateException("Service bag must be stated.");
	     }
	     */
		 
		 return SwingPoller1.instance;
		
	}
	
	private void set_frame1(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 150, 100);
		setTitle("AutoTSVR");
		
	}
	
	public void turn_on(){
		
		setVisible(true);
		polltimer.start();
		
	}
	
	public static void main(String[] args){
		
		SwingPoller1 frame = SwingPoller1.getInstance();
		frame.turn_on();
	}

	public void actionPerformed(ActionEvent e){
				
		if(e.getActionCommand().equals("Quit")){
			polltimer.stop();
			setVisible(false);
			// dispose();
			
		} else if (e.getActionCommand().equals("PollTimer")){

			polltimer.stop();
			
			indic_label.setText(String.format("Polling (%d sec.)", secdbl_from_start / 2));
			
			if(secdbl_from_start % 2 == 0)
				indic_label.setBackground(Color.YELLOW);
			else
				indic_label.setBackground(Color.GRAY);
			secdbl_from_start++;
		
			// NewFileAutoRead1 newfileautoread = NewFileAutoRead1.getInstance();
			// newfileautoread.scan_and_read();
			
			polltimer.start();
			
		}		
	}
}
