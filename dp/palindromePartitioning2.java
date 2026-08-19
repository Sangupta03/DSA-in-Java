import java.util.*;
class palindromePartitioning2 {
    public int minCut(String s) {
        int n=s.length();
        int[] dp=new int[n];
        Arrays.fill(dp,-1);
        pal=new Boolean[n+1][n+1];
        return solve(0,s,dp)-1;
    }

    public int solve(int i,String s,int[] dp){
        int n=s.length();
        if(i==n) return 0;
        
        if(dp[i]!=-1) return dp[i];
        int minCost=(int) 1e9;
        
        for(int j=i;j<n;j++){
            if(isPalindrome(i,j,s)){
                int cost=1+solve(j+1,s,dp);
                minCost=Math.min(minCost,cost);
            }
        }
        return dp[i]=minCost;

    }
    public Boolean[][] pal;
    public boolean isPalindrome(int i,int j,String s){
        int n=s.length();
        if(i>=j) return true;
        if(pal[i][j]!=null) return pal[i][j];
        if(s.charAt(i)==s.charAt(j)){
            return pal[i][j] = isPalindrome(i + 1, j - 1, s);
        }
        return pal[i][j]=false;
      
    }
}