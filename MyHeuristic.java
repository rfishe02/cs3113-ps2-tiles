
public class MyHeuristic extends Problem {

	// Count the number of displaced tiles it would take to move
	// each tile from it's starting point to its destination.
	//
	// Find the Euclidean distance from point A to point B.
	// Assume that we would displace at most 2 tiles, for each square we move from A to B,
	// by shifting the tiles clockwise.
	
	public int stepCost(Node child) {
		int res = 0;

		for (int a = 0; a < child.board.length; a++) {
			for (int b = 0; b < child.board[0].length; b++) {

				res += Math.sqrt(Math.pow((row[child.board[a][b]] - a),2) + Math.pow((col[child.board[a][b]] - b),2));

			}
		}

		return res * 2;
	}

}
