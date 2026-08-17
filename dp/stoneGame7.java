import java.util.*;
class stoneGame7 {
    public int stoneGameVII(int[] stones) {
        int n=stones.length;
        int[][] dp=new int[n][n];

        for(int[] row:dp){
            Arrays.fill(row,Integer.MIN_VALUE);
        }
        int[] prefix=new int[n+1];
        for(int i=0;i<n;i++){
            prefix[i+1]=prefix[i]+stones[i];
        }
        return solve(0,n-1,stones,dp,prefix);
    }

    public int solve(int i,int j,int[] stones,int[][] dp,int[] prefix){
        if(i==j) return 0;
        if(dp[i][j]!=Integer.MIN_VALUE) return dp[i][j];
        int leftSum=prefix[j+1]-prefix[i+1];
        int rightSum=prefix[j]-prefix[i];

        int choiceA=leftSum-solve(i+1,j,stones,dp,prefix);
        int choiceB=rightSum-solve(i,j-1,stones,dp,prefix);

        return dp[i][j]=Math.max(choiceA,choiceB);
    }
}