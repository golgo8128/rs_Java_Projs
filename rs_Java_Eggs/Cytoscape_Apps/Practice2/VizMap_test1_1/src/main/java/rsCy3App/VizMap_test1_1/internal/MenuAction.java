package rsCy3App.VizMap_test1_1.internal;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JOptionPane;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.presentation.property.NodeShapeVisualProperty;
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.view.vizmap.VisualStyleFactory;
import org.cytoscape.view.vizmap.mappings.DiscreteMapping;
import org.cytoscape.view.presentation.property.values.*;

// Refer to http://chianti.ucsd.edu/cytoscape-3.1.0/API/
// and http://wiki.cytoscape.org/Cytoscape_3/AppDeveloper/Cytoscape_3_App_Cookbook#VizMapper
// Also https://github.com/cytoscape/cytoscape-samples

/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	private final CyApplicationManager cyApplicationManager;
	private final CyNetworkManager cyNetworkManager;
	private final CyNetworkViewFactory networkViewFactory;
	private final CyNetworkViewManager networkViewManager;
	private final VisualMappingManager vmmServiceRef;
	private final VisualStyleFactory visualStyleFactoryServiceRef;
	private final VisualMappingFunctionFactory vmfFactoryC;
	private final VisualMappingFunctionFactory vmfFactoryD;
	private final VisualMappingFunctionFactory vmfFactoryP;
	
	public MenuAction(CyApplicationManager cyApplicationManager,
			CyNetworkManager cyNetworkManager,
			CyNetworkViewFactory networkViewFactory,
			CyNetworkViewManager networkViewManager,
			final String menuTitle,
			VisualMappingManager vmmServiceRef,
			VisualStyleFactory visualStyleFactoryServiceRef,
			VisualMappingFunctionFactory vmfFactoryC,
			VisualMappingFunctionFactory vmfFactoryD,
			VisualMappingFunctionFactory vmfFactoryP) {

		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps");
		
		this.cyApplicationManager = cyApplicationManager;
		this.cyNetworkManager     = cyNetworkManager;
		this.networkViewFactory   = networkViewFactory;
		this.networkViewManager   = networkViewManager;
		this.vmmServiceRef        = vmmServiceRef;
		this.visualStyleFactoryServiceRef = visualStyleFactoryServiceRef;
		this.vmfFactoryC = vmfFactoryC;
		this.vmfFactoryD = vmfFactoryD;		
		this.vmfFactoryP = vmfFactoryP;
		
	}

	public void actionPerformed(ActionEvent e) {
		
		// CyNetwork net1        = cyApplicationManager.getCurrentNetwork();
		CyNetwork net1;		
		CyNetwork[] nets = (CyNetwork[])cyNetworkManager.getNetworkSet().toArray(new CyNetwork[0]);
		net1 = nets[0];

		CyNetworkView[] netviews = (CyNetworkView[])networkViewManager.getNetworkViews(net1).toArray(new CyNetworkView[0]);		
		CyNetworkView netview = netviews[0];
		
		// To create a new VisualStyle object and set the mapping function
		VisualStyle vstyle = this.visualStyleFactoryServiceRef
				.createVisualStyle("RS Visual #1");		
		
		/* Node Shape: Discrete
		 * Depending on the node attribute "Node type",
		 * "Node type" is "Metabolite" --> hexagon
		 * "Node type" is "Enzyme"     --> rounded rectangle
		 * "Node type" is "Protein"    --> ellipse
 		 */
		
		DiscreteMapping<String, NodeShape> nodeShapeMapping
		= (DiscreteMapping<String, NodeShape>)this.vmfFactoryD
		.createVisualMappingFunction("Node type", String.class, BasicVisualLexicon.NODE_SHAPE);
		nodeShapeMapping.putMapValue("Metabolite", NodeShapeVisualProperty.HEXAGON);
		nodeShapeMapping.putMapValue("Enzyme", NodeShapeVisualProperty.ROUND_RECTANGLE);  
		nodeShapeMapping.putMapValue("Protein", NodeShapeVisualProperty.ELLIPSE);         	

		vstyle.addVisualMappingFunction(nodeShapeMapping);  
		
		/* Node Color: Discrete
		 * Depending on the node attribute "Node type",
		 * "Node type" is "Metabolite" --> red
		 * "Node type" is "Enzyme"     --> blue
		 * "Node type" is "Protein"    --> pink
 		 */ 

		
		
		/* Edge color: Discrete
		 * 
		 * 
		 * 
		 */
		
		
		
		/* Edge width: Continuous
		 * Control edge width based on the edge attribute named "Weight" using continuous mapping.
		 * For "Weight" < 5, edge width should be 1.
		 * For 5 <= "Weight" < 10, edge width should be in the range of [1, 11].
		 * For "Weight" >= 10, edge width should be 11.
		 */

		// Make two boundary range value sets, and do "add point".
		// One boundary value should be (1,1,1)
		// The second boundary value should be (11,11,11)
		

		// Add the new style to the VisualMappingManager
		vmmServiceRef.addVisualStyle(vstyle);
//		cyApplicationManager.setCurrentNetwork(net1);
//		cyApplicationManager.setCurrentNetworkView(netview);
//		cyApplicationManager.setSelectedNetworks(Arrays.<CyNetwork>asList(net1));
//		cyApplicationManager.setSelectedNetworkViews(Arrays.<CyNetworkView>asList(netview));
		vmmServiceRef.setCurrentVisualStyle(vstyle);
		
		
		// Apply the visual style to a NetworkView
		vstyle.apply(netview);
		netview.updateView();
		
		JOptionPane.showMessageDialog(null, "Applied to " + net1.toString()); // "VizMapper manipulated from the App!");

	}
}
