import java.util.HashSet;
import java.util.Random;

public class Problem {

	byte[][] start;
	byte[][] goal;
	int[][] actions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	int[] col = { 2, 0, 1, 2, 0, 1, 2, 0, 1 };
	int[] row = { 2, 0, 0, 0, 1, 1, 1, 2, 2 };

	//////
	// Get the goal state for the board.
	//////

	public void setGoalState(int dim) {
		goal = new byte[dim][dim];
		byte i = 1;

		for (int a = 0; a < goal.length; a++) {
			for (int b = 0; b < goal[0].length; b++) {
				goal[a][b] = i;
				i++;
			}
		}

		goal[goal.length - 1][goal.length - 1] = 0;
	}

	//////
	// Get the initial state of the board.
	//////

	public void setInitialState(int dim) {
		HashSet<Byte> used = new HashSet<>();
		Random rand = new Random();
		start = new byte[dim][dim];
		int spots = (start.length * start[0].length);
		int row = 0;
		int col = 0;
		int i;
		byte tmp;

		i = spots;

		while (i > 0) {
			tmp = (byte) (rand.nextInt(spots));

			if (!used.contains(tmp)) {
				start[row][col] = tmp;

				col++;
				if (col >= start[0].length) {
					col = 0;
					row++;
				}
				used.add(tmp);
				i--;
			}
		}
	}

	//////
	// Get the starting point.
	//////

	public int[] getStartPoint() {
		int[] st = new int[2];
		boolean res = true;
		int a = 0;

		while (res && a < start.length) {
			for (int b = 0; b < start[0].length; b++) {
				if (start[a][b] == 0) {
					st[0] = a;
					st[1] = b;
					res = false;
					break;
				}
			}
			a++;
		}

		return st;
	}

	//////
	// Check to see whether the state is the goal state.
	//////

	public boolean goalTest(Node u) {
		boolean result = true;

		int a = 0;
		while (result && a < u.board.length) {
			for (int b = 0; b < u.board[0].length; b++) {
				if (u.board[a][b] != goal[a][b]) {
					result = false;
					break;
				}
			}
			a++;
		}

		return result;
	}

	//////
	// Get result of action.
	//////

	public byte[][] result(Node parent, int[] action) {
		byte[][] res = parent.getDeepCopy();
		byte tmp;

		tmp = res[parent.pos[0] + action[0]][parent.pos[1] + action[1]];
		res[parent.pos[0] + action[0]][parent.pos[1] + action[1]] = 0;
		res[parent.pos[0]][parent.pos[1]] = tmp;

		return res;
	}

	//////
	// Calculate the step cost by adding up the Manhattan distance for each number.
	//////

	public int stepCost(Node child) {
		int res = 0;

		for (int a = 0; a < child.board.length; a++) {
			for (int b = 0; b < child.board[0].length; b++) {

				res += Math.abs(row[child.board[a][b]] - a) + Math.abs(col[child.board[a][b]] - b);

			}
		}

		return res;
	}

	//////
	// Get a string representation of the starting state.
	//////

	public String getStrStartState() {
		String state = "";

		for (int a = 0; a < start.length; a++) {
			for (int b = 0; b < start[0].length; b++) {
				state += start[a][b] + " ";
			}
		}

		return state.substring(0, state.length() - 1);
	}

}
