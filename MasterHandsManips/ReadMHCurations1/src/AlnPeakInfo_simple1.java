import emon2.api.PeakInfo;

public class AlnPeakInfo_simple1 {

	// public PeakGrp_simple1 pkgrp;
	// public EachPeak_simple1 epeak;
	public PeakInfo pkgrp;
	public PeakInfo epeak;
	public PeakInfo epeak_before_align;
	
	AlnPeakInfo_simple1(PeakInfo ipkgrp, PeakInfo iepeak){
		
		this.pkgrp = ipkgrp;
		this.epeak = iepeak;
		
	}

	AlnPeakInfo_simple1(PeakInfo iepeak){

		this.pkgrp = null;
		this.epeak = iepeak;
		
	}
	
	public void set_epeak_before_align(PeakInfo iepeak) {
		
		this.epeak_before_align = iepeak;
		
	}
	
}
