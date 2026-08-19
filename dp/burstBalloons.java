import java.util.*;
class burstBalloons {
    public int maxCoins(int[] nums) {
        int n=nums.length;
        int[][] dp=new int[n+2][n+2];
        int[] arr=new int[n+2];
        arr[0]=1;
        arr[n+1]=1; //add 1 to end for multiplication

        for(int i=0;i<n;i++){
            arr[i+1]=nums[i];
        }

        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return solve(1,n,arr,dp);
    }

    public int solve(int i,int j,int[] arr,int[][] dp){
        if(i>j) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int maxi=(int)-1e9;

        for(int k=i;k<=j;k++){
            int points=(arr[i-1]*arr[k]*arr[j+1])+solve(i,k-1,arr,dp)+solve(k+1,j,arr,dp);
            maxi=Math.max(maxi,points);
        }
        return dp[i][j]=maxi;
    }
}