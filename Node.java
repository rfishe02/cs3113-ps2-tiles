import java.util.LinkedList;

public class Node {
	LinkedList<Node> adj;
	Node pred;
	int num;
	
	public Node(int num) {
		adj = new LinkedList<>();
		this.num = num;
	}
	
	// n.STATE
	// n.PARENT
	// n.ACTION
	// n.PATH-COST
	
	// CHILD-NODE(problem,parent,action) returns a node
	// with
	//   STATE = problem.RESULT(parent.STATE,action)
	//   PARENT = parent, ACTION = action,
	//   PATH-COST = parent.PATH-COST + problem.STEP-COST(parent.STATE,action)
	
}
