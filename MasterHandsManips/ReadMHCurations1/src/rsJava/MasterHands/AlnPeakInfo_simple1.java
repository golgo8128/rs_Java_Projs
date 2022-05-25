package rsJava.MasterHands;

import emon2.api.AlignmentInfo;
import emon2.api.PeakInfo;
import emon2.api.SessionInfo;

public class AlnPeakInfo_simple1 {

	// public PeakGrp_simple1 pkgrp;
	// public EachPeak_simple1 epeak;
	
	public AlignmentInfo align;
	public SessionInfo sess;
	
	public PeakInfo pkgrp;
	public PeakInfo epeak;
	public PeakInfo epeak_before_align;
	
	AlnPeakInfo_simple1(
			AlignmentInfo ialign,
			SessionInfo isess,
			PeakInfo ipkgrp, PeakInfo iepeak
			){
		
		this.align = ialign;
		this.sess = isess;
		this.pkgrp = ipkgrp;
		this.epeak = iepeak;
		
	}

	AlnPeakInfo_simple1(
			AlignmentInfo ialign,
			SessionInfo isess,
			PeakInfo iepeak){

		this.align = ialign;
		this.sess = isess;
		this.pkgrp = null;
		this.epeak = iepeak;
		
	}
	
	public void set_epeak_before_align(PeakInfo iepeak) {
		
		this.epeak_before_align = iepeak;
		// Make sure that this belong to the session this.sess.
		
	}
	
}
