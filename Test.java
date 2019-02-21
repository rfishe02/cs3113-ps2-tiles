import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		
		Tree g = new Tree();
		
		// Create the inital problem state.
		
		byte[][] start = getInitialState();
		//printBoard(start);

		// Create a starting node with the inital state & a path cost of zero.
		Node nt = new Node(null,0);
		nt.deepCopyState(start);

		
		
	}
	
	//////
	// Get the initial state of the board.
	//////
	
	public static byte[][] getInitialState() {
		HashSet<Byte> used = new HashSet<>();
		Random rand = new Random();
		byte[][] board = new byte[3][3];
		int spots = (board.length * board[0].length) - 1;
		int row = 0;
		int col = 0;
		int i;
		byte tmp;
		
		i = spots;
		
		while(i > 0) {
			tmp = (byte)(rand.nextInt(spots)+1);
			
			if(!used.contains(tmp)) {
				board[row][col] = tmp;

				col++;
				if(col >= board[0].length) {
					col = 0;
					row++;
				}
				used.add(tmp);
				i--;
			}
		}
		
		return board;
	}
	
	// Use a greedy best-first search. Expands the nodes closest to the goal, using just
	// the heuristic function, f(n) = h(n).
	
	// A* : f(n) = g(n) + h(n)
	// It evaluates nodes by combining g(n), the cost to reach the node, and h(n), the
	// estimated cost of the cheapest path from n to the goal.
	
	// Try first the node with the lowest value of g(n) + h(n).
	
	// This algorithm is identical to uniform-cost-search, except
	// that A* uses g + h instead of g.
	
	// f(n) never overestimes the true cost of a solution.
	
	// Notes:
	// Store the sequence of moves as a String.
	// aStar keeps the frontier.
	
	//////////////////////////////////
	
	// TreeSearch
	// initialize the frontier using the initial state of the problem.
	// loop do
	//   if the frontier is empty then return failure.
	//   choose a leaf node and remove it from the frontier.
	//   if the node contains a goal state then return the solution.
	//   expand the chosen node, adding the resulting nodes to the frontier.
	
	// graphSearch
	// intitilize the frontier using the initial state of problem.
	// initilize the explored set to be empty.
	// loop do
	// 	if the frontier is empty then return failure
	//  choose a leaf node and remove it from the frontier
	//  if the leaf node contains a goal state, then return the solution.
	//  add the node to the explored set
	//  expand the chosen node, adding the results to the frontier, 
	//    only if not in the frontier or explored set.
	
	// Uniform-Cost-Search
	// node <- a node with STATE = problem.INITIAL-STATE, PATH-COST = 0
	// frontier <- a priority queue ordered by PATH-COST, with node as the only element
	// explored <- an empty set
	// loop do
	//   if EMPTY?(frontier) then return failure
	//   node <- POP(frontier) // chooses lowest cost node in frontier
	//   if problem.GOAL-TEST(node.STATE) then return SOLUTION(node)
	//   add node.STATE to explored
	//   for each action in problem.ACTIONS(node.STATE) do
	//     child <- CHILD-NODE(problem,node,action)
	//     if child.STATE is not in explored or frontier then
	//       frontier <- INSERT(child,frontier)
	//     else if child.STATE is in frontier with higher PATH-COST then
	//       replace that frontier node with child
	
	public void aStar(Node start, byte[][] inital) {
		PriorityQueue<Node> q = new PriorityQueue<>(1,new NodeComparator());
		HashSet<Node> explored = new HashSet<>();
		Node u;
		
		q.add(start);
		
		while(!q.isEmpty()) {
			
			u = q.remove();
			
			if(goalTest(u)) {
				q.clear();
			} else {
				
				explored.add(u);
				
				
				
			}
			
		}
		
	}
	
	public static boolean goalTest(Node u) {
		boolean result = true;
		
		byte[][] goal =
		{
		{1,2,3},
		{4,5,6},
		{7,8,0}
		};
		
		for(int a = 0; a < u.state.length-1; a++) {
			for(int b = 0; b < u.state[0].length-1; b++) {
				if(u.state[a][b]!=goal[a][b]) {
					result = false;
					break;
				}
			}
		}
		
		return result;
	}
	
	//////
	// Used for debugging.
	//////
	
	public static void printBoard(byte[][] board) {
		for(int a = 0; a < board.length; a++) {
			for(int b = 0; b < board[0].length; b++) {
				System.out.printf("%-3s  ",(board[a][b]+""));
			}
			System.out.println();
		}
	}
	
}
