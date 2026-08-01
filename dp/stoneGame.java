import java.util.*;
class stoneGame {
    public boolean stoneGameQ(int[] piles) {
        int n=piles.length;
        int[][] dp=new int[n][n];

        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return maxDiff(0,n-1,piles,dp)>=0;
    }

    public int maxDiff(int i,int j,int[] piles,int[][] dp){
        if(i==j) return piles[i]; //base case

        if(dp[i][j]!=-1) return dp[i][j];

        int choice1=piles[i]-maxDiff(i+1,j,piles,dp); //choose left side
        int choice2=piles[i]-maxDiff(i,j-1,piles,dp); //choose right side

        return dp[i][j]=Math.max(choice1,choice2); //each player make the max/optimal choice
    }
}