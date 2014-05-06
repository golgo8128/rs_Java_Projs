package rsCy3App.Cy3AutoReadTSV1_1.internal;

import java.awt.Color;
import java.awt.Paint;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.view.vizmap.mappings.BoundaryRangeValues;
import org.cytoscape.view.vizmap.mappings.ContinuousMapping;
import org.cytoscape.view.vizmap.mappings.PassthroughMapping;


// This class should be dealt as singleton.
public final class VizMap1_3_II {

	private final String vstyle_name = "TPN VStyle #1";
	private static VizMap1_3_II instance   = null;	
	private static VisualStyle vstyle   = null;
	private static RegServiceBag1_4 rSB = null;
	
	private VizMap1_3_II(RegServiceBag1_4 rSB){

		VizMap1_3_II.rSB = rSB;
		
		boolean vstyle_exist = false;
		for(VisualStyle ivstyle: rSB.vmmServiceRef.getAllVisualStyles()){
			if(ivstyle.getTitle().equals(vstyle_name)){
				vstyle = ivstyle;
				vstyle_exist = true;
				break;
			}
		}
		
		if(!vstyle_exist){
			set_VStyle_I();
		}
		
		rSB.vmmServiceRef.setCurrentVisualStyle(vstyle);
	}
	
	public static VisualStyle get_vstyle(){
		return VizMap1_3_II.vstyle;
	}
	
	public static synchronized VizMap1_3_II getInstance(RegServiceBag1_4 rSB){
		
		if(instance == null){ 
			instance = new VizMap1_3_II(rSB);
		}
		
		return instance;
	}	
	
	
	public static synchronized VizMap1_3_II getInstance(){
		
		 if(instance == null){ 
			 throw new IllegalStateException("Service bag must be stated.");
	     }
		 
		 return instance;
		
	}

	public void apply_VStyle_I(CyNetwork net1){

		RegServiceBag1_4 rSB = VizMap1_3_II.rSB;
		
		// System.out.println("Called apply_VStyle_I");
		
		CyNetworkView netview = rsCy3App_Usefuls1.take_first_view(rSB, net1);
			
		// Apply the visual style to a NetworkView
		rSB.vmmServiceRef.setCurrentVisualStyle(vstyle);
		vstyle.apply(netview);
		// netview.updateView();	
		
	}
	

	private void set_VStyle_I(){
		// Set the mapping functions
				
		/* Node symbol: Passthrough
		 * 
		 */
		
		vstyle = rSB.visualStyleFactoryServiceRef
				.createVisualStyle(vstyle_name);	// Creates a new VisualStyle object		
		
		PassthroughMapping<String, String> nodeLabelMapping = (PassthroughMapping<String, String>) rSB.vmfFactoryP
				.createVisualMappingFunction("Node symbol", String.class, BasicVisualLexicon.NODE_LABEL);
		vstyle.addVisualMappingFunction(nodeLabelMapping);
		
		
		// Set node color map to attribute "Score"
		ContinuousMapping<Double, Paint> mapping = (ContinuousMapping<Double, Paint>)
				rSB.vmfFactoryC.createVisualMappingFunction("Score", Double.class, BasicVisualLexicon.NODE_FILL_COLOR);

		// Define the points
		Double val1 = 0.0d;
		BoundaryRangeValues<Paint> brv1 = new BoundaryRangeValues<Paint>(Color.WHITE, Color.WHITE, Color.WHITE);

		Double val2 = 1.0d;
		BoundaryRangeValues<Paint> brv2 = new BoundaryRangeValues<Paint>(Color.RED, Color.RED, Color.RED);

		// Set the points
		mapping.addPoint(val1, brv1);
		mapping.addPoint(val2, brv2);

		// add the mapping to visual style            
		vstyle.addVisualMappingFunction(mapping); 		
		

		// Add the new style to the VisualMappingManager
		rSB.vmmServiceRef.addVisualStyle(vstyle);
			
	}
	
}
