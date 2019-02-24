
public class MyHeuristic extends Problem {

	public int stepCost(Node child) {
		int res = 0;
		
		for(int a = 0; a < child.board.length; a++) {
			for(int b = 0; b < child.board[0].length; b++) {

				res += Math.abs(row[child.board[a][b]] - a) + Math.abs(col[child.board[a][b]] - b);
				
			}
		}
		
		return res;
	}
	
}
