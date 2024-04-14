// TC: O(n^2)
// SC: O(n^2)

// Approach: Flatten the board to make it more managable. Start a BFS
// from 1 and try all 6 paths. Mark visited nodes and the first time
// you reach n^2 return the level you are at.

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        // flatten the board
        boolean goRight = true;
        int[] flattenedBoard = new int[n * n + 1];

        // flattened board counter
        int k = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (goRight) {
                for (int j = 0; j < n; j++) {
                    flattenedBoard[k] = board[i][j];
                    k++;
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    flattenedBoard[k] = board[i][j];
                    k++;
                }
            }
            goRight = !goRight;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        // starting position
        queue.add(1);
        visited.add(1);
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                int position = queue.poll();

                if (position == n * n) {
                    return moves;
                }

                for (int i = 1; position + i <= n * n && i <= 6; i++) {
                    int newPosition = position + i;

                    if (visited.contains(newPosition)) {
                        continue;
                    }

                    visited.add(newPosition);
                    if (flattenedBoard[newPosition] == -1) {
                        queue.add(newPosition);
                    } else {
                        // if new position is not a snake or a ladder; we can mark it visited
                        if (flattenedBoard[newPosition] == -1) {
                            visited.add(flattenedBoard[newPosition]);
                        }
                        queue.add(flattenedBoard[newPosition]);
                    }
                }

                size--;
            }

            moves++;
        }

        return -1;
    }
}