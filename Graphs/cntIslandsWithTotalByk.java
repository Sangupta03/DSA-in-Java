package Graphs;

class cntIslandsWithTotalByk {
    public int countIslands(int[][] grid, int k) {
        int n=grid.length;
        int m=grid[0].length;

        boolean[][] visited=new boolean[n][m];
        int ans=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]!=0 && !visited[i][j]){
                    long[] sum=new long[1];
                    dfs(i,j,grid,visited,sum);
                    if(sum[0]%k==0){
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public void dfs(int i,int j,int[][] grid,boolean[][] visited,long[] sum){

        int n=grid.length;
        int m=grid[0].length;
        if(i<0 || j<0 || i>=n || j>=m || visited[i][j] || grid[i][j]==0) return;

        visited[i][j]=true;
        sum[0]=sum[0]+grid[i][j];

        int[] dr={-1,0,1,0};
        int[] dc={0,1,0,-1};

        for(int d=0;d<4;d++){
            int nrow=dr[d]+i;
            int ncol=dc[d]+j;
            dfs(nrow,ncol,grid,visited,sum);
        }
    }
}