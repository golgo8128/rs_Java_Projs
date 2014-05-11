// This is from http://www.javadrive.jp/tutorial/timer/index1.html

package rsCy3App.Cy3NetTSVAutoImport1_2.internal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;

import rsCy3App.Cy3NetTSVAutoImport1_2.internal.rs_Java_Proj4.general.fileproc.NewFileAddedWatcher1_2;
import rsCy3App.Cy3NetTSVAutoImport1_2.internal.rs_Java_Proj4.general.strproc.StrUtil1;

public final class SwingPoller1_2 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final SwingPoller1_2 instance = new SwingPoller1_2();
		
	public static Timer polltimer;
	
	private static JPanel mainPanel;
	public static JLabel indic_label;
	private static JButton quit_button;
	
	private static int secdbl_from_start;
	private static final int sec_rythm = 3;
	
	public static RegServiceBag1_4 rSB = null;

	public static NewFileAddedWatcher1_2 newfileaddedwatcher;
	public static final String[] tpn_path_split = {".rs_Progs_data", "Cytoscape", "TPN", "attribs"};	
	public static final String[] filnam_ends = {"_ok.txt", "_nodes.tsv", "_edges.tsv"}; // Must be in this order.
	// *_ok.txt must be created at last step. Note that *_ok.txt may not be complete at the time of polling.	
	
	private SwingPoller1_2(){
		
		set_frame1();

		JLabel title_label = new JLabel();
		title_label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		title_label.setText("Cy3Net TSV Auto Import");
		title_label.setOpaque(true);
		title_label.setAlignmentX(Component.CENTER_ALIGNMENT);

		indic_label = new JLabel();
		indic_label.setFont(new Font("Courier New", Font.PLAIN, 11));
		indic_label.setText("Initializing ...");
        indic_label.setOpaque(true);
        indic_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit_button = new JButton("Quit");
		quit_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		quit_button.addActionListener(this);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(Box.createRigidArea(new Dimension(1,5)));
		mainPanel.add(title_label);
		mainPanel.add(Box.createRigidArea(new Dimension(1,5)));
		mainPanel.add(indic_label);
		mainPanel.add(Box.createRigidArea(new Dimension(1,5)));
		mainPanel.add(quit_button);

		getContentPane().add(mainPanel, BorderLayout.CENTER);		
		
		polltimer = new Timer(sec_rythm*1000 , this);		
		polltimer.setActionCommand("PollTimer");
		secdbl_from_start = 0;
		
		File watch_path = new File(System.getProperty("user.home"), (StrUtil1.strjoin(tpn_path_split, File.separator)));
		newfileaddedwatcher = new NewFileAddedWatcher1_2(watch_path, filnam_ends);		
		
	}; 	
		
	public static SwingPoller1_2 getInstance(RegServiceBag1_4 rSB){

		SwingPoller1_2.rSB = rSB;
		return SwingPoller1_2.instance;
	}		
	
	public static SwingPoller1_2 getInstance(){
		
		 if(rSB == null){ 
			 throw new IllegalStateException("Service bag must be stated.");
	     }
		 
		 return SwingPoller1_2.instance;
		
	}
	
	private void set_frame1(){
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 50, 200, 120);
		setTitle("Net TSV Import");
		
	}
	
	public void turn_on(){
		
		setVisible(true);
		polltimer.start();
		
	}
	
	public static void main(String[] args){
		
		RegServiceBag1_4 rSB = null;
		
		SwingPoller1_2 frame = SwingPoller1_2.getInstance(rSB);
		frame.turn_on();
	}

	public void actionPerformed(ActionEvent e){
				
		if(e.getActionCommand().equals("Quit")){
			polltimer.stop();
			setVisible(false);
			// dispose();
			
		} else if (e.getActionCommand().equals("PollTimer")){

			polltimer.stop();

			// Timer display
			String indicstr = new String();
			
			for(int i = 0;i < 4;i ++){
				if(i < (secdbl_from_start/sec_rythm) % 4)
					indicstr += ".";
				else
					indicstr += " ";
			}
			
			indic_label.setText("Polling " + indicstr);
			secdbl_from_start += sec_rythm;
			
			
			HashSet<String> new_fpath_wo_ends = newfileaddedwatcher.get_new_file_paths_wo_ends();
			
			if(new_fpath_wo_ends.size() > 0){
				Task nfa_task = new NewFileAutoRead_task1(new_fpath_wo_ends);
				rSB.taskManager.execute(new TaskIterator(nfa_task));				
				// polltimer should be started at the end of the task.
			} else {
				indic_label.setBackground(mainPanel.getBackground()); // Color.LIGHT_GRAY);
				polltimer.start();
			}

		}		
	}
}
