package rsCy3App.rsMetabPPI1_12_2.internal;

import java.awt.Color;
import java.awt.Paint;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.presentation.property.NodeShapeVisualProperty;
import org.cytoscape.view.presentation.property.values.NodeShape;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.view.vizmap.mappings.BoundaryRangeValues;
import org.cytoscape.view.vizmap.mappings.ContinuousMapping;
import org.cytoscape.view.vizmap.mappings.DiscreteMapping;
import org.cytoscape.view.vizmap.mappings.PassthroughMapping;

// This class should be dealt as singleton.
public final class VizMap1_3 {

	
	private static VizMap1_3 instance   = null;	
	private static VisualStyle vstyle   = null;
	private static RegServiceBag1_4 rSB = null;
	
	private VizMap1_3(RegServiceBag1_4 rSB){

		VizMap1_3.rSB = rSB;
		vstyle = rSB.visualStyleFactoryServiceRef
				.createVisualStyle("MPPI VStyle #1");	// Creates a new VisualStyle object
		set_VStyle_I();
		
	}
	
	public static VisualStyle get_vstyle(){
		return VizMap1_3.vstyle;
	}
	
	public static synchronized VizMap1_3 getInstance(RegServiceBag1_4 rSB){
		
		if(instance == null){ 
			instance = new VizMap1_3(rSB);
		}
		
		return instance;
	}	
	
	
	public static synchronized VizMap1_3 getInstance(){
		
		 if(instance == null){ 
			 throw new IllegalStateException("Service bag must be stated.");
	     }
		 
		 return instance;
		
	}

	public void apply_VStyle_I(CyNetwork net1){

		RegServiceBag1_4 rSB = VizMap1_3.rSB;
		
		// System.out.println("Called apply_VStyle_I");
		
		CyNetworkView netview = rsCy3App_Usefuls1.take_first_view(rSB, net1);
			
		// Apply the visual style to a NetworkView
		vstyle.apply(netview);
		// netview.updateView();	
		
	}
	

	private void set_VStyle_I(){
		// Set the mapping functions
		
		/* Node Shape: Discrete
		 * Depending on the node attribute "Node type",
		 * "Node type" is "Metabolite" --> hexagon
		 * "Node type" is "Enzyme"     --> rounded rectangle
		 * "Node type" is "Protein"    --> ellipse
 		 */
		
		DiscreteMapping<String, NodeShape> nodeShapeMapping
			= (DiscreteMapping<String, NodeShape>)rSB.vmfFactoryD
				.createVisualMappingFunction("Node type", String.class, BasicVisualLexicon.NODE_SHAPE);
		nodeShapeMapping.putMapValue("Metabolite", NodeShapeVisualProperty.HEXAGON);
		nodeShapeMapping.putMapValue("Enzyme", NodeShapeVisualProperty.ROUND_RECTANGLE);  
		nodeShapeMapping.putMapValue("Protein", NodeShapeVisualProperty.ELLIPSE);         	

		vstyle.addVisualMappingFunction(nodeShapeMapping);  
		
		/* Node symbol: Passthrough
		 * 
		 */
		
		PassthroughMapping<String, String> nodeLabelMapping = (PassthroughMapping<String, String>) rSB.vmfFactoryP
				.createVisualMappingFunction("Node symbol", String.class, BasicVisualLexicon.NODE_LABEL);
		vstyle.addVisualMappingFunction(nodeLabelMapping);
		
		
		/* Node Color: Discrete
		 * Depending on the node attribute "Node type",
		 * "Node type" is "Metabolite" --> red
		 * "Node type" is "Enzyme"     --> blue
		 * "Node type" is "Protein"    --> pink
 		 */ 

		DiscreteMapping<String, Paint> nodeColorMapping
			= (DiscreteMapping<String, Paint>)rSB.vmfFactoryD
				.createVisualMappingFunction("Node type", String.class, BasicVisualLexicon.NODE_FILL_COLOR);
		nodeColorMapping.putMapValue("Metabolite", new Color(0xff,0x00,0x00));
		nodeColorMapping.putMapValue("Enzyme", new Color(0x00,0xcc,0xcc));  
		nodeColorMapping.putMapValue("Protein", new Color(0xff,0x99,0x99));         	

		vstyle.addVisualMappingFunction(nodeColorMapping); 		

		
		/* Node Border Color: Discrete
		 * (based on node selection)
 		 */ 

		DiscreteMapping<Boolean, Paint> nodeBorderColorMapping
			= (DiscreteMapping<Boolean, Paint>)rSB.vmfFactoryD
				.createVisualMappingFunction(CyNetwork.SELECTED, Boolean.class, BasicVisualLexicon.NODE_BORDER_PAINT);
		nodeBorderColorMapping.putMapValue(true, new Color(0xff,0xff,0x00));
      	
		vstyle.addVisualMappingFunction(nodeBorderColorMapping); 			

		/* Node Border Width: Discrete
		 * (based on node selection)
 		 */ 

		DiscreteMapping<Boolean, Double> nodeBorderWidthMapping
			= (DiscreteMapping<Boolean, Double>)rSB.vmfFactoryD
				.createVisualMappingFunction(CyNetwork.SELECTED, Boolean.class, BasicVisualLexicon.NODE_BORDER_WIDTH);
		nodeBorderWidthMapping.putMapValue(true, 15d);
      	
		vstyle.addVisualMappingFunction(nodeBorderWidthMapping); 			
		
		
		
		/* Edge color: Discrete
		 * 
		 */

		DiscreteMapping<String, Paint> edgeColorMapping
			= (DiscreteMapping<String, Paint>)rSB.vmfFactoryD
				.createVisualMappingFunction("interaction", String.class, BasicVisualLexicon.EDGE_STROKE_UNSELECTED_PAINT);
		edgeColorMapping.putMapValue("Metabolism", new Color(0xd0,0x00,0xd0));
		edgeColorMapping.putMapValue("Metabolism (Curated)", new Color(0xd0,0x00,0x00));  
		edgeColorMapping.putMapValue("PPI", new Color(0x00,0x66,0xff));         	

		vstyle.addVisualMappingFunction(edgeColorMapping); 		
		
		
		/* Edge width: Continuous
		 * Control edge width based on the edge attribute named "Weight" using continuous mapping.
		 * For "Weight" = 1, edge width should be 0.5.
		 * For 1 <= "Weight" < 5, edge width should be in the range of [0.5, 3].
		 * For "Weight" >= 5, edge width should be 3.
		 */
		// Make two boundary range value sets, and do "add point".
		// One boundary value should be (0.5,0.5,0.5)
		// The second boundary value should be (3.0,3.0,3.0)
		
		ContinuousMapping<Integer, Double> edgeWidthMapping
		= (ContinuousMapping<Integer, Double>)rSB.vmfFactoryC
		.createVisualMappingFunction("weight", Integer.class, BasicVisualLexicon.EDGE_WIDTH);		

		final BoundaryRangeValues<Double> bvew0 = new BoundaryRangeValues<Double>(0.5d, 0.5d, 0.5d);
		final BoundaryRangeValues<Double> bvew1 = new BoundaryRangeValues<Double>(3.0d, 3.0d, 3.0d);

		edgeWidthMapping.addPoint(1, bvew0);
		edgeWidthMapping.addPoint(5, bvew1);
		vstyle.addVisualMappingFunction(edgeWidthMapping);		
			
		// Add the new style to the VisualMappingManager
		rSB.vmmServiceRef.addVisualStyle(vstyle);
		rSB.vmmServiceRef.setCurrentVisualStyle(vstyle);		
		
	}
	
}
