//--------------------------------------------------------------------------------------
//GraphicalPartFactory는 다른 EditPart를 생성하기 위한 factory
//--------------------------------------------------------------------------------------

package gef.EditPart;

import gef.Model.Model;
import gef.Model.NodeModel;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

public class GraphicalPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart iContext, Object iModel) {
		System.out.println("GraphicalPartFactory.createEditPart(" + iContext + "," + iModel + ")");
		
		EditPart editPart = null;
		if (iModel instanceof Model) {
			editPart = new RootEditPart();
		}
		else if (iModel instanceof NodeModel) {
			editPart = new NodeEditPart();
		}
		
		
		if (editPart != null) {
			editPart.setModel(iModel);
		}
		return editPart;
	}
}