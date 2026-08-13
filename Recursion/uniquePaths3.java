package Recursion;

class uniquePaths3 {
    public int uniquePathsIII(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        boolean[][] vis=new boolean[n][m];
        int sc=0;
        int sr=0; //start points
        int total=0; //non obstacle parts
        int[] cnt=new int[1];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]!=-1){
                    total+=1; //keep in mind walk over every non obstacle path
                }
                if(grid[i][j]==1){
                    sr=i;
                    sc=j;
                }
            }
        }
        solve(sr,sc,grid,vis,cnt,1,total);  //start with visited=1 IMP
        return cnt[0];
    }

    public void solve(int row,int col,int[][] grid,boolean[][] vis,int[] cnt,int visited,int total){

        int n=grid.length;
        int m=grid[0].length;

        if(row<0 || col<0 || row>=n || col>=m || grid[row][col]==-1 || vis[row][col]){
            return;
        }

        if(grid[row][col]==2){
            if(visited==total){
                cnt[0]++;
            }
            return;
        }
        int[] dr={1,0,-1,0};
        int[] dc={0,-1,0,1};

        vis[row][col]=true;

        for(int i=0;i<4;i++){
            solve(row+dr[i],col+dc[i],grid,vis,cnt,visited+1,total);
        }
        vis[row][col]=false;
    }
}