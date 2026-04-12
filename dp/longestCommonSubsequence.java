class longestCommonSubsequence {
    static int lcs(String s1, String s2) {
        
        int n=s1.length();
        int m=s2.length();
        int[][] dp=new int[n+1][m+1];
        
        for(int i=0;i<=n;i++) dp[i][0]=0;
        for(int j=0;j<=m;j++) dp[0][j]=0;
        
       
        
        for(int idx1=1;idx1<=n;idx1++){
            for(int idx2=1;idx2<=m;idx2++){
                if(s1.charAt(idx1-1)==s2.charAt(idx2-1)){
                    dp[idx1][idx2]=1+dp[idx1-1][idx2-1];
                }else{
                    dp[idx1][idx2]=Math.max(dp[idx1-1][idx2],dp[idx1][idx2-1]);
                }
            }
        }
       
        return dp[n][m];
    }
}