package Graphs;
import java.util.*;
class numberDistinctIslands {

    int countDistinctIslands(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    StringBuilder sb = new StringBuilder();
                    dfs(i, j, i, j, grid, vis, sb);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    void dfs(int r, int c, int r0, int c0,
             int[][] grid, boolean[][] vis, StringBuilder sb) {

        vis[r][c] = true;

        // store relative position
        sb.append(r - r0).append(",").append(c - c0).append(";");

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length &&
                !vis[nr][nc] && grid[nr][nc] == 1) {

                dfs(nr, nc, r0, c0, grid, vis, sb);
            }
        }
    }
}

