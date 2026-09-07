import java.util.*;
class minASCIIdeleteSum {
    public int minimumDeleteSum(String s1, String s2) {
        int n=s1.length();
        int m=s2.length();

        int[][] dp=new int[n+1][m+1];
        for(int[] arr:dp){
            Arrays.fill(arr,-1);
        }
        return findLCS(s1,s2,n,m,dp);
    }

    public int findLCS(String s1,String s2,int i,int j,int[][] dp){
       
        if (i == 0 && j == 0) return 0;
        if(i==0){
            dp[i][j]=s2.charAt(j-1)+findLCS(s1,s2,i,j-1,dp);
            //so delete all char in s2
        }
        if(j==0){
            dp[i][j]=s1.charAt(i-1)+findLCS(s1,s2,i-1,j,dp);
            //s2 finished , so delete all char in s1
        }
        if(dp[i][j]!=-1) return dp[i][j];

        int take=Integer.MAX_VALUE;
        if(s1.charAt(i-1)==s2.charAt(j-1)){
            take=0+findLCS(s1,s2,i-1,j-1,dp);
        }
        int notTake=Math.min((int)s1.charAt(i-1)+findLCS(s1,s2,i-1,j,dp),(int)s2.charAt(j-1)+findLCS(s1,s2,i,j-1,dp));
        return dp[i][j]=Math.min(take,notTake);
    }
}