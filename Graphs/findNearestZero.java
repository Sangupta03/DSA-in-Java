package Graphs;
import java.util.*;
class findNearestZero {

    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        boolean[][] vis = new boolean[n][m];
        int[][] ans = new int[n][m];

        Queue<Triple> q = new LinkedList<>();

        // Push all 0 cells into queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new Triple(i, j, 0));
                    vis[i][j] = true;
                }
            }
        }

        int[] delRow = { -1, 0, 1, 0 };
        int[] delCol = { 0, 1, 0, -1 };

        while (!q.isEmpty()) {
            Triple cur = q.poll();
            int r = cur.row;
            int c = cur.col;
            int step = cur.steps;

            ans[r][c] = step;

            for (int i = 0; i < 4; i++) {
                int nRow = r + delRow[i];
                int nCol = c + delCol[i];

                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && !vis[nRow][nCol]) {
                    vis[nRow][nCol] = true;
                    q.offer(new Triple(nRow, nCol, step + 1));
                }
            }
        }

        return ans;
    }
}

class Triple {
    int row;
    int col;
    int steps;

    Triple(int row, int col, int steps) {
        this.row = row;
        this.col = col;
        this.steps = steps;
    }
}
