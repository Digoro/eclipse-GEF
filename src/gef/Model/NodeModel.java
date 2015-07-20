//--------------------------------------------------------------------------------------
//Node Model은 GEF 애플리케이션의 figure와 일치한다.
//label, size, position등과 같은 필요한 properties를 저장한다.
//--------------------------------------------------------------------------------------

package gef.Model;

import java.util.ArrayList;
import java.util.List;

public class NodeModel {
	private List<NodeConnectionModel> sourceConnections = new ArrayList<NodeConnectionModel>();
	private List<NodeConnectionModel> targetConnections = new ArrayList<NodeConnectionModel>();

	public NodeModel(String s) {
		label = s;
	}
 
	public String getLabel() {
		return label;
	}
 
	private final String label;
 
 
	public List<NodeConnectionModel> getSourceConnections() {
		return sourceConnections;
	}
 
	public List<NodeConnectionModel> getTargetConnections() {
		return targetConnections;
	}
 
	public void addSourceConnection(NodeConnectionModel iConnection) {
		sourceConnections.add(iConnection);
	}
 
	public void addTargetConnection(NodeConnectionModel iConnection) {
		targetConnections.add(iConnection);
	}
}