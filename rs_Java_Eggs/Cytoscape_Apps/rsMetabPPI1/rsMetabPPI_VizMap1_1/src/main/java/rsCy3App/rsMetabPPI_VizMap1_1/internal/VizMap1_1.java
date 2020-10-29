package rsCy3App.rsMetabPPI_VizMap1_1.internal;

import java.awt.Color;
import java.awt.Paint;

import javax.swing.JOptionPane;

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

public class VizMap1_1 {

	private RegServiceBag1_2 rSB;
	
	public VizMap1_1(RegServiceBag1_2 rSB){
		this.rSB = rSB;
	}

	public void apply_VStyle_I(){

		RegServiceBag1_2 rSB = this.rSB;
		
		// CyNetwork net1        = cyApplicationManager.getCurrentNetwork();
		CyNetwork net1;		
		CyNetwork[] nets = (CyNetwork[])rSB.cyNetworkManager.getNetworkSet().toArray(new CyNetwork[0]);
		net1 = nets[0];

		CyNetworkView[] netviews = (CyNetworkView[])rSB.networkViewManager.getNetworkViews(net1).toArray(new CyNetworkView[0]);		
		CyNetworkView netview = netviews[0];
		
		// To create a new VisualStyle object and set the mapping function
		VisualStyle vstyle = rSB.visualStyleFactoryServiceRef
				.createVisualStyle("RS Visual #1");		
		
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
		nodeColorMapping.putMapValue("Enzyme", new Color(0x00,0x00,0xff));  
		nodeColorMapping.putMapValue("Protein", new Color(0xff,0x00,0xff));         	

		vstyle.addVisualMappingFunction(nodeColorMapping); 		
		
		
		/* Edge color: Discrete
		 * 
		 */

		DiscreteMapping<String, Paint> edgeColorMapping
			= (DiscreteMapping<String, Paint>)rSB.vmfFactoryD
				.createVisualMappingFunction("interaction", String.class, BasicVisualLexicon.EDGE_STROKE_UNSELECTED_PAINT);
		edgeColorMapping.putMapValue("Metabolism", new Color(0xff,0x00,0xff));
		edgeColorMapping.putMapValue("Metabolism (Curated)", new Color(0xff,0x00,0x00));  
		edgeColorMapping.putMapValue("PPI", new Color(0x00,0x00,0xff));         	

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
		
		
		// Apply the visual style to a NetworkView
		vstyle.apply(netview);
		netview.updateView();
	
		JOptionPane.showMessageDialog(null, "Visual style RS Visual #1 applied to " + net1.toString());		
		
	}
	
	
}
