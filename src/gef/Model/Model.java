//--------------------------------------------------------------------------------------
//모델은 모양에 대한 데이터를 저장한다.
//이 예제에서는 root, node, connection 모델이 있다.
//root 모델은 다른 모든 모델을 포함하는 최상위 모델이다
//some node models, connections를 생성한다.
//--------------------------------------------------------------------------------------
package gef.Model;
import java.util.ArrayList;
import java.util.List;

public class Model {
	private List<NodeModel> nodes;
 
	public Model() {
 
		nodes = new ArrayList<NodeModel>();
 
		for (int i = 0; i < 3; i++) {
			NodeModel node = new NodeModel("Node " + i );
			nodes.add(node);
		}
 
		//set the connection here
		for (int i = 0; i < 2; i++) {
			NodeConnectionModel connection = new NodeConnectionModel();
 
			connection.setSource((NodeModel) nodes.get(i));
			connection.setTarget((NodeModel) nodes.get(i +1));
 
			((NodeModel) nodes.get(i)).addSourceConnection(connection);
			((NodeModel) nodes.get(i +1)).addTargetConnection(connection);
		}
 
	}
 
	public List<NodeModel> getNodes() {
		return nodes;
	}
}