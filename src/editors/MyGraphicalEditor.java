package editors;

import java.io.File;
import java.net.URI;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalShift;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

public class MyGraphicalEditor extends GraphicalEditorWithPalette {
	public MyGraphicalEditor() {
	}
	public ToolBarManager tm;
	public static final String ID = "FileBrowser.myGraphicalEditor";
	private Graph graph;
	private GraphConnection conn;
	private GraphConnection childConn;
	PaletteRoot root = new PaletteRoot();

	private SelectionListener selectionListener = new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.item);
			// ((GraphNode) e.item).
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
		}
	};
	
	///////////////////////////////////////////////////////////
	@Override
	protected PaletteRoot getPaletteRoot() {
		if(root==null)
			root= getPaletteRoot();
		return root;
	}
	protected PaletteViewerProvider createPaletteViewerProvider() {
	    return new PaletteViewerProvider(getEditDomain()) {
	    		protected void configurePaletteViewer(PaletteViewer viewer) {
	            super.configurePaletteViewer(viewer);
	            viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
	        }
	    };
	}
	protected void initializePaletteViewer(){
			super.initializePaletteViewer();
			getPaletteViewer().addDragSourceListener(
			new TemplateTransferDragSourceListener(getPaletteViewer())
		);
	}
	///////////////////////////////////////////////////////////
	
	public void createPartControl(Composite parent) {
		PaletteViewCreator pc = new PaletteViewCreator();
		pc.createPaletteRoot();
		parent.setLayout(new GridLayout(1, false));
		ToolBar toolbar = new ToolBar(parent, SWT.None);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1, 1));
		ToolBarManager tm = new ToolBarManager(toolbar);
		this.tm = tm;
		((IMenuService) getEditorSite().getService(IMenuService.class)).populateContributionManager(tm,"toolbar:FileBrowser.MyGraphicalEditor");
	
		FileStoreEditorInput fsInput = (FileStoreEditorInput) getEditorInput();
		URI uri = fsInput.getURI();
		File file = new File(uri);
		
		graph = new Graph(parent, SWT.NONE);
		//mouse wheel zoom in out
		graph.addMouseWheelListener( new MouseWheelListener() {
			@Override
			public void mouseScrolled(org.eclipse.swt.events.MouseEvent e) {
				 if (( e.stateMask & SWT.CTRL ) == 0)
	                    return;     
	                if (e.count > 0) {
					System.out.println("Zoom In");
					graph.getRootLayer().setScale(graph.getRootLayer().getScale()*1.1f);
	                    //viewer.getGraphControl().getZoomManager().zoomOut();
	                } else if (e.count < 0) {
						System.out.println("Zoom Out");
						graph.getRootLayer().setScale(graph.getRootLayer().getScale()*0.9f);
	                    //viewer.getGraphControl().getZoomManager().zoomIn();
	                }
			}
        } );
		graph.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		// graph resize > redraw
		graph.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (graph.getLayoutAlgorithm().getClass().equals(HorizontalTreeLayoutAlgorithm.class)) {
					graph.setLayoutAlgorithm(new HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
				} else if (graph.getLayoutAlgorithm().getClass().equals(TreeLayoutAlgorithm.class)) {
					graph.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
				} else if (graph.getLayoutAlgorithm().getClass().equals(HorizontalShift.class)) {
					graph.setLayoutAlgorithm(new HorizontalShift(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
				} else if (graph.getLayoutAlgorithm().getClass().equals(GridLayoutAlgorithm.class)) {
					graph.setLayoutAlgorithm(new GridLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
				}
			}
		});
		Image folderImage = new Image(parent.getDisplay(),"C:/Users/lee/workspace/FileBrowser/icons/Folder.ico");
		Image fileImage = new Image(parent.getDisplay(),"C:/Users/lee/workspace/FileBrowser/icons/File.ico");

		GraphNode root = new GraphNode(graph, SWT.NONE, file.getName(),folderImage);
		if (file.listFiles() != null) {
			for (File childFile : file.listFiles()) {
				if (childFile.isDirectory()) {
					GraphNode childNode = new GraphNode(graph, SWT.NONE,childFile.getName(), folderImage);
					conn = new GraphConnection(graph,ZestStyles.CONNECTIONS_SOLID, root, childNode);
					if (childFile.listFiles() != null) {
						for (File grandChildFile : childFile.listFiles()) {
							if (grandChildFile.isDirectory()) {
								GraphNode grandChildNode = new GraphNode(graph,SWT.NONE, grandChildFile.getName(),folderImage);
								childConn = new GraphConnection(graph,ZestStyles.CONNECTIONS_SOLID,childNode, grandChildNode);
							} else {
								GraphNode grandChildNode = new GraphNode(graph,SWT.NONE, grandChildFile.getName(),fileImage);
								childConn = new GraphConnection(graph,ZestStyles.CONNECTIONS_SOLID,childNode, grandChildNode);
							}
						}
					}
				} else {
					GraphNode childNode = new GraphNode(graph, SWT.NONE,childFile.getName(), fileImage);
					conn = new GraphConnection(graph,ZestStyles.CONNECTIONS_SOLID, root, childNode);
				}
			}
		}
		graph.setLayoutAlgorithm(new TreeLayoutAlgorithm(
				LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
		graph.addSelectionListener(selectionListener);
	}
	@Override
	public void setFocus() {
	}
	public ToolBarManager getToolBarManager() {
		return tm;

	}
	public Graph getGraph() {
		return graph;
	}
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
	}
	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
	}
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		setSite(site);
		setInput(input);
		setPartName(input.getName());
	}
	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected void initializeGraphicalViewer() {
		super.initializePaletteViewer();
		getPaletteViewer().addDragSourceListener(
					new TemplateTransferDragSourceListener(getPaletteViewer()));
	}
}
