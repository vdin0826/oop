// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        int minRow = rows, maxRow = -1;
        int minCol = cols, maxCol = -1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == ch) {
                    if (r < minRow) minRow = r;
                    if (r > maxRow) maxRow = r;
                    if (c < minCol) minCol = c;
                    if (c > maxCol) maxCol = c;
                }
            }
        }

        if (maxRow == -1) {
            return 0;
        }
        return (maxRow - minRow + 1) * (maxCol - minCol + 1); // YOUR CODE HERE
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isPlusCenter(r, c)) {
                    count++;
                }
            }
        }
        return count;
    }
    private boolean isPlusCenter(int r, int c) {
        char ch = grid[r][c];
        int up = branchLen(r, c, -1, 0, ch);
        int down = branchLen(r, c, 1, 0, ch);
        int left = branchLen(r, c, 0, -1, ch);
        int right = branchLen(r, c, 0, 1, ch);
        int minLen = Math.min(Math.min(up, down), Math.min(left, right));
        return minLen >= 2;
    }
    private int branchLen(int r, int c, int dr, int dc, char ch) {
        int len = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int nr = r + dr;
        int nc = c + dc;
        while (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == ch) {
            len++;
            nr += dr;
            nc += dc;
        }
        return len; // YOUR CODE HERE
	}
	
}
