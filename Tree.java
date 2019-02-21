import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Tree {
	
	// CHILD-NODE(problem,parent,action) returns a node
	// with
	//   STATE = problem.RESULT(parent.STATE,action)
	//   PARENT = parent, ACTION = action,
	//   PATH-COST = parent.PATH-COST + problem.STEP-COST(parent.STATE,action)
	
	public Node childNode() {
		
		Node res = null;
		
		return res;
		
	}

}
