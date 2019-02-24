
public class Node extends Object {

	// n.STATE
	// n.PARENT
	// n.PATH-COST
	// n.ACTION
	
	Node parent; 
	
	byte[][] board; 
	int[] pos;
	
	int[] action;	
	int pathCost; 

	public Node(int pathCost) {
		this.pathCost = pathCost;
	}
	
	////
	// Setters and getters.
	////
	
	public void setDeepCopy(byte[][] board) {
		this.board = new byte[board.length][board[0].length];

		for(int a = 0; a < board.length; a++) {
			for(int b = 0; b < board[0].length; b++) {
				this.board[a][b] = board[a][b];
			}
		}
	}
	
	public byte[][] getDeepCopy() {
		byte[][] res = new byte[this.board.length][this.board[0].length];

		for(int a = 0; a < this.board.length; a++) {
			for(int b = 0; b < this.board[0].length; b++) {
				res[a][b] = this.board[a][b];
			}
		}
		return res;
	}
	
	public void setAction(int[] action) {
		this.action = action;
		
		pos = new int[2];
		
		pos[0] = parent.pos[0]+action[0];
		pos[1] = parent.pos[1]+action[1];
	}

	////
	// Override the equals & hashcode method to make it easier to compare states.
	////

	@Override
	public boolean equals(Object obj) {
		boolean res = true;
		Node n = (Node)obj;
		
		int a = 0;
		while(res && a < board.length) {
			for(int b = 0; b < board[0].length; b++) {
				if(board[a][b] != n.board[a][b]) {
					res = false;
					break;
				}
			}
			a++;
		}

		return res;
	}
	
	@Override
	public int hashCode() {
		String state = "";
		
		for(int a = 0; a < board.length; a++) {
			for(int b = 0; b < board[0].length; b++) {
				state += board[a][b]+"";
			}
		}

		return state.hashCode();
	}
	
}
