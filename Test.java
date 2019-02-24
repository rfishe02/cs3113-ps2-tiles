import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		
		Problem g = new Problem();
		
		// Create the inital problem state.
		
		Problem p = new Problem();
		p.setInitialState(3);
		p.setGoalState(3);

		// Create a starting node with the inital state & a path cost of zero.
		
		Node s = new Node(0);
		s.setDeepCopy(p.start);
		s.pos = p.getStartPoint();
		
		Node res = aStar(p,s);
		
		if(res != null) {
			printBoard(res.board);
			
			Node current = res;
			while(current.parent != null) {
				System.out.println(current.action[0]+" "+current.action[1]);
				current = current.parent;
			}
			
		} else {
			System.out.println("No solution found.");
		}
		
	}

	//////
	// aStar.
	//////

	public static Node aStar(Problem p, Node start) {
		PriorityQueue<Node> q = new PriorityQueue<>(1,new NodeComparator());
		HashSet<Node> visited = new HashSet<>();
		HashMap<Node,Integer> visitingCost = new HashMap<>();
		Node u;
		Node v;

		q.add(start);
		
		while(!q.isEmpty()) {
			
			u = q.remove();
			
			if(p.goalTest(u)) {
				return u;
			} else {
				
				visitingCost.remove(u);
				visited.add(u);
				
				for(int[] a : p.actions) {
					
					if(a[0] + u.pos[0] >= 0 && a[0] + u.pos[0] < u.board.length) {
						if(a[1] + u.pos[1] >= 0 && a[1] + u.pos[1] < u.board[0].length) {
							
							v = childNode(p,u,a);
							
							if(!visited.contains(v)) {
								
								if(visitingCost.containsKey(v)) {
									
									// Because of the way the hash code function
									// works for the Node class, this will look
									// for the exact state in the HashMap.
								
									if(v.pathCost < visitingCost.get(v)) {
										
										// This will remove the duplicate,
										// because the queue will find
										// the node with a duplicate state,
										// because Node overrides .equals().
										
										q.remove(v); 
										q.add(v); // Add the child with the lower path cost.
									}
									
								} else {
									q.add(v);
									visitingCost.put(v, v.pathCost);
								}
								
							}
							
						}
					}
					
				}
				
				
			}
			
		}
		
		return null;
	}

	//////
	// Create a child node.
	//////
	
	public static Node childNode(Problem p, Node parent, int[] action) {
		Node child = new Node(0);
		
		child.board = p.result(parent, action);
		
		child.parent = parent;
		child.setAction(action);
		
		child.pathCost = parent.pathCost + p.stepCost(child);
		
		return child;
	}
		
	//////
	// Used for debugging.
	//////
	
	public static void printBoard(byte[][] board) {
		System.out.println();
		for(int a = 0; a < board.length; a++) {
			for(int b = 0; b < board[0].length; b++) {
				System.out.printf("%-3s  ",(board[a][b]+""));
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
