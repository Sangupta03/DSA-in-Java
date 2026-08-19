import java.util.*;
class minCostToCutStick {
    public int minCost(int n, int[] cuts) {
        int len=cuts.length;
        int[] arr=new int[len+2];
        arr[0]=0;
        arr[len+1]=n;
        for(int i=0;i<len;i++){
            arr[i+1]=cuts[i];
        }
        int[][] dp=new int[len+2][len+2];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        Arrays.sort(arr);
        return solve(1,len,arr,dp);
    }
    public int solve(int i,int j,int[] arr,int[][] dp){
        if(i>j) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int mini=(int)1e9;

        for(int k=i;k<=j;k++){
            int cost=(arr[j+1]-arr[i-1])+solve(i,k-1,arr,dp)+solve(k+1,j,arr,dp);
            mini=Math.min(cost,mini);
        }
        return dp[i][j]=mini;
    }
}
