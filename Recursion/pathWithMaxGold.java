package Recursion;

class pathWithMaxGold {
    public int getMaximumGold(int[][] grid) {
        int maxi=0;
        int n=grid.length;
        int m=grid[0].length;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                
                if(grid[i][j]!=0){
                    int cnt=solve(i,j,grid);
                    maxi=Math.max(cnt,maxi);
                }
            }
        }
        return maxi;
    }

    public int solve(int row,int col,int[][] grid){
        int n=grid.length;
        int m=grid[0].length;

        if(row>=n || col>=m || row<0 || col<0 || grid[row][col]==0){
            return 0;
        }

        int[] dr={1,0,-1,0};
        int[] dc={0,-1,0,1};
        int temp=grid[row][col];
        grid[row][col]=0;

        int maxi=0;
        for(int i=0;i<4;i++){
            int gold=solve(row+dr[i],col+dc[i],grid);
            maxi=Math.max(gold,maxi); //IMP
        }

        grid[row][col]=temp; //unmark
        return temp+maxi;  //IMP
    }
}