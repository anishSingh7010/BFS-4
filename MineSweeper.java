// TC: O(m * n)
// SC: O(m * n)

// Approach: If click is on a mine, mark it as 'X' else
// start a BFS from the click and keep adding 'B' mines.
// When you process a 'B'; count the mines around it. If there is
// even a single mine; update the value and stop. Else carry on with the BFS

import java.util.LinkedList;
import java.util.Queue;

class MineSweeper {
    int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } };

    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0];
        int col = click[1];

        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { row, col });
        board[row][col] = 'B';

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int mines = getMines(board, r, c);

            if (mines == 0) {
                // add all paths
                for (int[] dir : dirs) {
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];

                    if (newRow >= 0 && newCol >= 0 && newRow < board.length && newCol < board[0].length
                            && board[newRow][newCol] == 'E') {
                        queue.add(new int[] { newRow, newCol });
                        board[newRow][newCol] = 'B';
                    }
                }
            } else {
                // update mines
                board[r][c] = (char) (mines + '0');
            }
        }

        return board;
    }

    private int getMines(char[][] board, int row, int column) {
        int mines = 0;

        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = column + dir[1];

            if (newRow >= 0 && newCol >= 0 && newRow < board.length && newCol < board[0].length
                    && board[newRow][newCol] == 'M') {
                mines++;
            }
        }

        return mines;
    }
}