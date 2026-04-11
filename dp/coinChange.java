import java.util.*;
class coinChangeQ {
    public int coinChange(int[] coins, int amount) {
        int n=coins.length;

        int[][] dp=new int[n][amount+1];

        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        int res=solve(n-1,coins,amount,dp);
        return res==(int)1e9?-1:res;
    }

    public int solve(int idx,int[] coins,int amount,int[][] dp){

        if(idx==0){
            return (amount%coins[idx]==0)?amount/coins[0]:(int) 1e9;
        }
        if(dp[idx][amount]!=-1) return dp[idx][amount];

        int notTake=solve(idx-1,coins,amount,dp);
        int take=(int) 1e9;

        if(coins[idx]<=amount) take=1+solve(idx,coins,amount-coins[idx],dp);
        return dp[idx][amount]=Math.min(take,notTake);
    }
}