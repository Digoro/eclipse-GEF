package gef;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

public class ChangeLayoutStyleHandler extends AbstractHandler {
	int layoutState = 1;
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IViewPart view = (View) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("GEFTutorial.view2");
		
		if(layoutState == 1){
			((View) view).getGraph().setLayoutAlgorithm(new HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
			System.out.println("Changed HorizontalTreeLayoutAlgorithm");
			layoutState += 1;
		}
		else{
			((View) view).getGraph().setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
			System.out.println("Changed TreeLayoutAlgorithm");
			layoutState -= 1;
		}
		
		return null;
	}
	
	public int getLayoutState(){
		return layoutState;
	}
	
}
