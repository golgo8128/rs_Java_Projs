package rsCy3App.Cy3NetTSVAutoImport1_2.internal;

import javax.swing.Timer;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

public class NewFileAutoRead_task1 extends AbstractTask {

	private RegServiceBag1_4 rSB;
	private Timer polltimer;
	
	public NewFileAutoRead_task1(RegServiceBag1_4 rSB,
			Timer polltimer){
		
		this.rSB       = rSB;
		this.polltimer = polltimer;
		
	}
	
	@Override
	public void run(TaskMonitor arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
