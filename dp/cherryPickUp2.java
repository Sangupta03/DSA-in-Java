import java.util.*;
class cherryPickUp2 {
    public int cherryPickup(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][][] dp=new int[n][m][m];

        for(int[][] twod: dp){
            for(int[] oned:twod){
                Arrays.fill(oned,-1);
            }
        }
        return solve(grid,0,0,m-1,dp);

    }

    public int solve(int[][] grid, int i,int j1,int j2,int[][][] dp){

        int n=grid.length;
        int m=grid[0].length;
        //out of bounds
        if(j1<0 || j1>=m || j2<0 || j2>=m){
            return (int) -1e9;
        }

        //reaches destination
        if(i==n-1){
            if(j1==j2) return grid[i][j1];
            else return grid[i][j1]+grid[i][j2];
        }
        if(dp[i][j1][j2]!=-1) return dp[i][j1][j2];

        //all possible paths
        int maxi=(int) -1e9;
        for(int d1=-1;d1<=1;d1++){
            for(int d2=-1;d2<=1;d2++){
                int value=(int) 1e9;
                if(j1==j2) value=grid[i][j1]+solve(grid,i+1,j1+d1,j2+d2,dp);
                else value=grid[i][j1]+grid[i][j2]+solve(grid,i+1,j1+d1,j2+d2,dp);
                maxi=Math.max(value,maxi);
            }
        }
        return dp[i][j1][j2]=maxi;
    }
}