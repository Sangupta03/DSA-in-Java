package Graphs;

class islandPerimeter {
    public int islandPerimeterQ(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        boolean[][] vis=new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!vis[i][j] && grid[i][j]==1){
                    return dfs(i,j,grid,vis);
                }
            }
        }
        return 0;
    }
    public int dfs(int row,int col,int[][] grid,boolean[][] vis){

        int n=grid.length;
        int m=grid[0].length;

        //case 1: goes outside boundary
        if(row<0 || col<0 || row>=n || col>=m) return 1;

        //case 2: land to water
        if(grid[row][col]==0) return 1;

        //case 3: land to land
        if(vis[row][col]) return 0;

        vis[row][col]=true;
        int[] dr={-1,0,+1,0};
        int[] dc={0,1,0,-1};
        int ans=0;

        for(int i=0;i<4;i++){
            int nrow=row+dr[i];
            int ncol=col+dc[i];

            ans+=dfs(nrow,ncol,grid,vis);
        }
        return ans;
    }
}