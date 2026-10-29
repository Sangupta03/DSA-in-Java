class minXORPathInGrid {
    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][][] dp = new boolean[n][m][1024];

        solve(0, 0, grid, grid[0][0], dp);

        for (int x = 0; x < 1024; x++) {
            if (dp[n - 1][m - 1][x]) {
                return x;
            }
        }

        return -1;
    }

    public void solve(int i, int j, int[][] grid, int xor,
                      boolean[][][] dp) {
        int n = grid.length;
        int m = grid[0].length;

        if (dp[i][j][xor]) {
            return;
        }

        dp[i][j][xor] = true;

        if (i == n - 1 && j == m - 1) {
            return;
        }

        if (i + 1 < n) {
            solve(i + 1, j, grid, xor ^ grid[i + 1][j], dp);
        }

        if (j + 1 < m) {
            solve(i, j + 1, grid, xor ^ grid[i][j + 1], dp);
        }
    }
}