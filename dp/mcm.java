import java.util.*;
class mcm {
    static int matrixMultiplication(int arr[]) {
        // code here
        int n=arr.length;
        int[][] dp=new int[n][n];
        
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return solve(1,n-1,arr,dp);
    }
    
    public static int solve(int i,int j,int[] arr,int[][] dp){
        if(i==j) return 0;
        
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int mini=(int)1e9;
        for(int k=i;k<j;k++){
            int cost=arr[i-1]*arr[k]*arr[j]+solve(i,k,arr,dp)+solve(k+1,j,arr,dp);
            mini=Math.min(cost,mini);
        }
        return dp[i][j]=mini;
        
    }
}
//memo
class Solution {
    static int matrixMultiplication(int arr[]) {
        // code here
        int n=arr.length;
        int[][] dp=new int[n][n];
        
        for(int i=n-1;i>=1;i--){
            for(int j=i+1;j<n;j++){
                int mini=(int)1e9;
                for(int k=i;k<j;k++){
                    int cost=arr[i-1]*arr[k]*arr[j]+dp[i][k]+dp[k+1][j];
                    mini=Math.min(cost,mini);
                }
                dp[i][j]=mini;
            }
        }
        return dp[1][n-1];
    }
  }