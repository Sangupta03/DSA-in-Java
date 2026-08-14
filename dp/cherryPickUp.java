import java.util.*;
class cherryPickUp {
    public int cherryPickup(int[][] grid) {
        int n=grid.length;
        //can think of two people starting from (0,0) going through two diff paths to reach end
        int maxSteps=(n-1)+(n-1); //each person can take
        int[][][] dp=new int[n][n][maxSteps+1];
        for(int[][] twoD:dp){
            for(int[] oneD:twoD){
                Arrays.fill(oneD,-1);
            }
        }
        int ans=solve(0,0,0,grid,dp,maxSteps);
        return ans<0?0:ans;
    }

    public int solve(int row1,int row2,int steps,int[][] grid,int[][][] dp,int maxSteps){
        int n=grid.length;
     
        int col1=steps-row1;
        int col2=steps-row2;

        if(row1>=n || col1>=n || col2>=n || row2>=n || grid[row1][col1]==-1 || grid[row2][col2]==-1){
            return (int) -1e9;
        }
        if(maxSteps==steps){
            return grid[n-1][n-1];
        }
        if (dp[row1][row2][steps] != -1) return dp[row1][row2][steps];
        int maxi=(int)-1e9;
        for(int d1=0;d1<=1;d1++){
            for(int d2=0;d2<=1;d2++){
                int value=(int) 1e9;
                if(row1==row2){
                    value=grid[row1][col1]+solve(row1+d1,row2+d2,steps+1,grid,dp,maxSteps); //only 1 picks up cherry
                }else{
                    value=grid[row1][col1]+grid[row2][col2]+solve(row1+d1,row2+d2,steps+1,grid,dp,maxSteps);
                }
                maxi=Math.max(value,maxi);
            }
        }
        return dp[row1][row2][steps]=maxi;
    }
}