import java.util.*;
class dungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int n=dungeon.length;
        int m=dungeon[0].length;

        int[][] dp=new int[n][m];

        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return solve(0,0,dungeon,dp);  //start from (0,0) since knight needs to know his health is enough or not to enter the next cell
    }

    public int solve(int row,int col,int[][] dungeon,int[][] dp){
        int n=dungeon.length;
        int m=dungeon[0].length;

        if(row<0 || col<0 || row>=n || col>=m){
            return (int) 1e9;
        }
        if(row==n-1 && col==m-1){
            return Math.max(1,1-dungeon[row][col]);
        }
        if(dp[row][col]!=-1) return dp[row][col];
        int right=(-dungeon[row][col])+solve(row,col+1,dungeon,dp);
        int down=(-dungeon[row][col])+solve(row+1,col,dungeon,dp);

        int minHealth=Math.min(right,down);
        return dp[row][col]=Math.max(1,minHealth); //IMP health cant drop below 0;
    }
}