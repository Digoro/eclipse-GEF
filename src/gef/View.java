package gef;

import org.eclipse.draw2d.FanRouter;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

public class View extends ViewPart {
	public static final String ID = "GEFTutorial.view";
	private Graph graph;

	static class NewGraphNode extends GraphNode {
		public NewGraphNode(Graph graph, int none, String string) {
			super(graph, none, string);
			setSize(100, 60);
		}

	}

	@Override
	public void createPartControl(Composite parent) {
		
		
		graph = new Graph(parent, SWT.NONE);
		@SuppressWarnings("unused")
		GraphNode node1 = new NewGraphNode(graph, SWT.NONE, "Node 1");
		GraphNode node2 = new NewGraphNode(graph, SWT.NONE, "Node 2");
		GraphNode node3 = new NewGraphNode(graph, SWT.NONE, "Node 3");
		GraphNode node4 = new NewGraphNode(graph, SWT.NONE, "Node 4");
		GraphNode node5 = new NewGraphNode(graph, SWT.NONE, "Node 5");
		
		/*@SuppressWarnings("unused")
		IFigure figure1 = node1.getNodeFigure();
		IFigure figure2 = node2.getNodeFigure();
		IFigure figure3 = node3.getNodeFigure();
		IFigure figure4 = node4.getNodeFigure();
		IFigure figure5 = node5.getNodeFigure();*/	

		/*PolylineConnection c1 = new PolylineConnection();
		PolylineConnection c2 = new PolylineConnection();
		PolylineConnection c3 = new PolylineConnection();
		PolylineConnection c4 = new PolylineConnection();*/
		
		/*ChopboxAnchor sourceAnchor2 = new ChopboxAnchor(figure2);
		ChopboxAnchor sourceAnchor3 = new ChopboxAnchor(figure3);
		ChopboxAnchor targetAnchor3 = new ChopboxAnchor(figure3);
		ChopboxAnchor sourceAnchor4 = new ChopboxAnchor(figure4);
		ChopboxAnchor targetAnchor4 = new ChopboxAnchor(figure4);
		ChopboxAnchor targetAnchor5 = new ChopboxAnchor(figure5);
		ChopboxAnchor targetAnchor5_1 = new ChopboxAnchor(figure5);*/
		
		/*c1.setSourceAnchor(sourceAnchor2);
		c1.setTargetAnchor(targetAnchor3);
		c2.setSourceAnchor(sourceAnchor2);
		c2.setTargetAnchor(targetAnchor4);
		c3.setSourceAnchor(sourceAnchor3);
		c3.setTargetAnchor(targetAnchor5);
		c4.setSourceAnchor(sourceAnchor4);
		c4.setTargetAnchor(targetAnchor5_1);*/
		
		/*gc4.getConnectionFigure().setSourceAnchor(new ChopboxAnchor(figure4)); 
		gc4.getConnectionFigure().setTargetAnchor(new ChopboxAnchor(figure5));*/
		
		GraphConnection  gc1 = new GraphConnection(graph, ZestStyles.CONNECTIONS_SOLID, node2, node3);
		GraphConnection  gc2 = new GraphConnection(graph, ZestStyles.CONNECTIONS_SOLID, node2, node4);
		GraphConnection  gc3 = new GraphConnection(graph, ZestStyles.CONNECTIONS_SOLID, node3, node5);
		GraphConnection  gc4 = new GraphConnection(graph, ZestStyles.CONNECTIONS_SOLID, node4, node5);

	
		// Manhattan Connection 
		FanRouter f = new FanRouter(); 
		ManhattanConnectionRouter m = new ManhattanConnectionRouter(); 
		
		gc1.getConnectionFigure().setConnectionRouter(f); 
		f.route(gc1.getConnectionFigure()); 
		f.setNextRouter(m); 
		
		gc2.getConnectionFigure().setConnectionRouter(f); 
		f.route(gc2.getConnectionFigure()); 
		f.setNextRouter(m); 
		
		gc3.getConnectionFigure().setConnectionRouter(f); 
		f.route(gc3.getConnectionFigure()); 
		f.setNextRouter(m); 
		
		gc4.getConnectionFigure().setConnectionRouter(f); 
		f.route(gc4.getConnectionFigure()); 
		f.setNextRouter(m); 
		
		graph.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
		
		/*Figure figure = new Figure();
		figure.add((IFigure) node1);
		Figure figure2 = new Figure();
		figure2.add((IFigure) node2);*/
		/*new GraphConnection(graph, ZestStyles.CONNECTIONS_SOLID, node2, node3);
		new GraphConnection(graph, ZestStyles.CONNECTIONS_SOLID, node2, node4);
		new GraphConnection(graph, ZestStyles.CONNECTIONS_SOLID, node3, node5);
		new GraphConnection(graph, ZestStyles.CONNECTIONS_SOLID, node4, node5);*/	    
		/*IFigure rectangle = new RectangleFigure();
		Rectangle bounds = new Rectangle(50, 50, 50, 50);
		rectangle.setBackgroundColor(new Color(null, 200, 200, 200));
		rectangle.setBounds(bounds);
		PolylineConnection c = new PolylineConnection();
		ChopboxAnchor sourceAnchor = new ChopboxAnchor(figure);
		ChopboxAnchor targetAnchor = new ChopboxAnchor(figure2);
		c.setSourceAnchor(sourceAnchor);
		c.setTargetAnchor(targetAnchor);*/
		/*IFigure rectangle2 = new RectangleFigure();
		Rectangle bounds2 = new Rectangle(50, 50, 50, 50);
		rectangle2.setBackgroundColor(new Color(null, 200, 200, 200));
		rectangle2.setBounds(bounds2);
		PolylineConnection c = new PolylineConnection();
		ChopboxAnchor sourceAnchor = new ChopboxAnchor((IFigure) node1);
		ChopboxAnchor targetAnchor = new ChopboxAnchor((IFigure) node2);
		c.setSourceAnchor(sourceAnchor);
		c.setTargetAnchor(targetAnchor);*/
	}

	@Override
	public void setFocus() {
	}
}