import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Test {

	public static void main(String[] args) {
		
		Graph g = new Graph();
		int st = 1;
		int dest = 8;
		
		// Build a graph using a file in this format:
		// 1 2-75,3-23,4-21
		// nodeA NodeB-Weight,NodeC-Weight,...
		
		boolean result = g.buildGraph("list.txt");
		
		if(result) {
			
			//g.printAdjList();
			
			if(g.getNode(st) != null && g.getNode(dest) != null) {
				
				aStar(g,g.getNode(st),g.getNode(dest));
				
			} else {
				System.out.println("The starting point or destination doesn't exist.");
			}
			
		} else {
			System.out.println("Incorrect file format specified.");
		}

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
	
	//////////////////////////////////
	
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
	
	public void treeSearch() {
		
		// initialize the frontier using the initial state of the problem.
		// loop do
		//   if the frontier is empty then return failure.
		//   choose a leaf node and remove it from the frontier.
		//   if the node contains a goal state then return the solution.
		//   expand the chosen node, adding the resulting nodes to the frontier.
		
	}
	
	public void graphSearch() {
		// intitilize the frontier using the initial state of problem.
		// initilize the explored set to be empty.
	
		// loop do
		// 	if the frontier is empty then return failure
		//  choose a leaf node and remove it from the frontier
		//  if the leaf node contains a goal state, then return the solution.
		//  add the node to the explored set
		//  expand the chosen node, adding the results to the frontier, 
		//    only if not in the frontier or explored set.
		
	}

	public static void aStar(Graph g, Node st, Node dest) {
		Queue<Node> q = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();
		Node u;
		
		q.add(st);
		
		while(!q.isEmpty()) {
			
			u = q.remove();
			
			if(u.num == dest.num) {
				q.clear(); // Clear the queue when we find the destination.
			} else {
				
				// Look at the nearby nodes & add the unvisted nodes.
				for(Node v : u.adj) {
					if(!visited.contains(v.num)) {
						v.pred = u;
						visited.add(v.num);
						q.add(v);
					}
				}
			}
			
		}
		
	}
	
}
