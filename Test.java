import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {

		test(0,100,"first_results.txt");

	}
	
	//////
	// Run a series of tests using a problem with a particular heuristic.
	//////
	
	public static void test(int type, int trials, String filename) {
		
		try {

			BufferedWriter bw = new BufferedWriter(new PrintWriter(filename));
			Problem prob;
			Node start;
			Solution sol;
			
			bw.write("trial,init_state,found,steps,solution\n");

			// Run the test & print the average result.

			for (int i = 0; i < trials; i++) {
				bw.write(i+",");
				
				// Create the inital problem state.

				if(type == 0) {
					prob = new Problem();
				} else {
					prob = new MyHeuristic();
				}
				prob.setInitialState(3);
				prob.setGoalState(3);

				// Create a starting node with the inital state & a path cost of zero.

				start = new Node(0);
				start.setDeepCopy(prob.start);
				start.pos = prob.getStartPoint();

				Node res = aStar(prob, start);
				bw.write(prob.getStrStartState()+",");

				if (res != null) {
					sol = solution(res);
					bw.write(1+","+sol.size+","+sol.steps);
					System.out.printf("%2d : Solution found.\n",i);
				} else {
					bw.write(0+","+"NA,"+"NA");
					System.out.printf("%2d : No solution found.\n",i);
				}
				bw.write("\n");
			}

			bw.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

	//////
	// aStar.
	//////

	public static Node aStar(Problem p, Node start) {
		PriorityQueue<Node> frontier = new PriorityQueue<>(1, new NodeComparator());
		HashSet<Node> visited = new HashSet<>();
		HashMap<Node, Integer> visitingCost = new HashMap<>();
		Node u;
		Node v;

		frontier.add(start);

		while (!frontier.isEmpty()) {

			u = frontier.remove();

			if (p.goalTest(u)) {
				return u;
			}

			visitingCost.remove(u);
			visited.add(u);

			for (int[] a : p.actions) {

				if (validAction(u, a)) {
					v = childNode(p, u, a);

					if (!visited.contains(v)) {
						if (visitingCost.containsKey(v) && v.pathCost < visitingCost.get(v)) {
							frontier.remove(v); // Remove the duplicate child node.
							frontier.add(v); // Add the child with the lower path cost.

						} else {
							frontier.add(v);
							visitingCost.put(v, v.pathCost);
						}
					}
				}
				// End for loop.
			}

			// End while loop.
		}

		return null;
	}

	//////
	// Determine if action is valid.
	//////

	public static boolean validAction(Node u, int[] action) {
		boolean res = false;
		if (action[0] + u.pos[0] >= 0 && action[0] + u.pos[0] < u.board.length) {
			if (action[1] + u.pos[1] >= 0 && action[1] + u.pos[1] < u.board[0].length) {
				res = true;
			}
		}
		return res;
	}

	//////
	// Create a child node.
	//////

	public static Node childNode(Problem p, Node parent, int[] action) {
		Node child = new Node(0);

		child.board = p.result(parent, action); // Create a new board state from the parent + action.
		child.parent = parent;
		child.setAction(action);

		child.pathCost = parent.pathCost + p.stepCost(child); // Determine the cost of the action.

		return child;
	}

	//////
	// Methods used to display the solution.
	//////

	public static Solution solution(Node n) {
		Stack<String> s = new Stack<>();
		Solution sol = new Solution();
		
		Node current = n;
		while (current.parent != null) {
			s.push(getLabel(current.action));
			current = current.parent;
		}

		sol.size = s.size();

		while (!s.isEmpty()) {
			sol.steps += s.pop() + " ";
		}
		sol.steps = sol.steps.substring(0,sol.steps.length()-1);
		
		return sol;
	}

	public static String getLabel(int[] action) {

		if (action[0] == -1) {
			return "UP";
		} else if (action[0] == 1) {
			return "DOWN";
		} else if (action[1] == -1) {
			return "LEFT";
		} else {
			return "RIGHT";
		}

	}

	//////
	// Used for debugging.
	//////

	public static void printBoard(byte[][] board) {
		System.out.println();
		for (int a = 0; a < board.length; a++) {
			for (int b = 0; b < board[0].length; b++) {
				System.out.printf("%-3s  ", (board[a][b] + ""));
			}
			System.out.println();
		}
		System.out.println();
	}

}
