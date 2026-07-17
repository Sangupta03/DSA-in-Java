package Graphs;

class maxAreaOfIsland {
    public int maxAreaOfIslandQ(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        boolean[][] vis=new boolean[n][m];
        int maxCnt=0;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!vis[i][j] && grid[i][j]==1){
                    int[] cnt = new int[1];
                    dfs(i,j,vis,grid,cnt);
                    maxCnt=Math.max(maxCnt,cnt[0]);
                }
            }
        }
        return maxCnt;
    }

    public void dfs(int i,int j,boolean[][] vis,int[][] grid,int[] cnt){
        int n=grid.length;
        int m=grid[0].length;

        if(i<0 || i>=n || j<0 || j>=m || vis[i][j] || grid[i][j]==0){
            return;
        }
        vis[i][j]=true;
        cnt[0]++;

        dfs(i,j-1,vis,grid,cnt);
        dfs(i-1,j,vis,grid,cnt);
        dfs(i,j+1,vis,grid,cnt);
        dfs(i+1,j,vis,grid,cnt);
    }
}