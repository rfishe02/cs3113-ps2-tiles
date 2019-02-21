
public class Node {

	byte[][] state; // n.STATE
	Node parent; // n.PARENT
	String action; // n.ACTION
	int pathCost; // n.PATH-COST
	
	public Node(String action, int pathCost) {
		this.action = action;
		this.pathCost = pathCost;
	}
	
	public static Node[] actions() {
		return null;
	}
	
	////
	// Setters and getters.
	////
	
	public void shallowCopyState(byte[][] state) {
		this.state = state;
	}
	
	public void deepCopyState(byte[][] state) {
		this.state = new byte[state.length][state[0].length];

		for(int a = 0; a < state.length; a++) {
			for(int b = 0; b < state[0].length; b++) {
				this.state[a][b] = state[a][b];
			}
		}
	}
}
