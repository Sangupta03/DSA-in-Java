import java.util.*;
class stoneGame5 {
    public int stoneGameV(int[] stoneValue) {
        int n=stoneValue.length;
        int[][] dp=new int[n][n];

        int[] prefix=new int[n+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        for(int i=0;i<n;i++){
            prefix[i+1]=prefix[i]+stoneValue[i];
        }
        //dp[i][j] = max score obtainable starting from the subarray arr[i..j]
        return solve(0,n-1,stoneValue,dp,prefix);
    }

    public int solve(int i,int j,int[] stoneValue,int[][] dp,int[] prefix){
        if(i==j){
            return 0;
            //only one value left;
        }
        if(dp[i][j]!=-1) return dp[i][j];
        int best=0;
        for(int k=i;k<j;k++){
            int leftSum=prefix[k+1]-prefix[i];
            int rightSum=prefix[j+1]-prefix[k+1];
            if(leftSum<rightSum){
                best=Math.max(best,leftSum+solve(i,k,stoneValue,dp,prefix));
            }else if(rightSum<leftSum){
                best=Math.max(best,rightSum+solve(k+1,j,stoneValue,dp,prefix));
            }else{
                best=Math.max(best,leftSum+Math.max(solve(i,k,stoneValue,dp,prefix),solve(k+1,j,stoneValue,dp,prefix)));
            }
        }
        return dp[i][j]=best;
    }
}
