import java.util.*;
class distinctSubsequence {
    public int numDistinct(String s, String t) {
        int n=s.length();
        int m=t.length();

        int[] prev=new int[m+1];

        for(int i=0;i<=n;i++) prev[0]=1;
        for(int j=1;j<=m;j++) prev[j]=0;

        for(int idx1=1;idx1<=n;idx1++){
            int[] temp=new int[m+1];
            temp[0]=1;
            for(int idx2=1;idx2<=m;idx2++){
                if(s.charAt(idx1-1)==t.charAt(idx2-1)){
                    temp[idx2]=prev[idx2-1]+prev[idx2];
                }else{
                    temp[idx2]=prev[idx2];
                }
            }
            prev=temp;
        }
        return prev[m]; 
    }
}

//memo
class Solution {
    public int numDistinct(String s, String t) {
        int n=s.length();
        int m=t.length();

        int[][] dp=new int[n][m];
        for(int[] oned:dp){
            Arrays.fill(oned,-1);
        }

        return solve(n-1,m-1,s,t,dp);
    }

    //coin change family problem
    public int solve(int i,int j,String s,String t,int[][] dp){
        if(j<0) return 1; //entire t is found
        if(i<0) return 0; //entire s is processed

        if(dp[i][j]!=-1) return dp[i][j];

        if(s.charAt(i)==t.charAt(j)){
            return dp[i][j]=solve(i-1,j-1,s,t,dp)+solve(i-1,j,s,t,dp);
        }
        return dp[i][j]=solve(i-1,j,s,t,dp);
    }
}