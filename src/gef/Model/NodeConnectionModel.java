//--------------------------------------------------------------------------------------
//Node Connection Model은 connection link와 일치한다.
//--------------------------------------------------------------------------------------

package gef.Model;

public class NodeConnectionModel {
	 
	private NodeModel source;
	private NodeModel target;
 
	public void setSource(NodeModel s) {
		source = s;
	}
 
	public void setTarget(NodeModel t) {
		target = t;
	}
}