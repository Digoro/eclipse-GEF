package gef.EditPart;

import gef.Model.Model;
import gef.Model.NodeModel;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public class RootEditPart extends AbstractGraphicalEditPart {
	protected IFigure createFigure() {

		Figure f = new FreeformLayer();
		f.setLayoutManager(new FreeformLayout());

		f.setBorder(new MarginBorder(1));
		// Create a layout for the graphical screen
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.horizontalSpacing = 40;
		gridLayout.verticalSpacing = 40;
		gridLayout.marginHeight = 20;
		gridLayout.marginWidth = 20;
		f.setLayoutManager(gridLayout);
		f.setOpaque(true);
		return f;
	}

	protected void createEditPolicies() {

	}

	protected List<NodeModel> getModelChildren() {
		List<NodeModel> children = ((Model) getModel()).getNodes();
		return children;
	}
}