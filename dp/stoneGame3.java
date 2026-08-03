import java.util.*;
class stoneGame3 {
    public String stoneGameIII(int[] stoneValue) {
        int n=stoneValue.length;
        int[] dp=new int[n+1];
        Arrays.fill(dp,Integer.MIN_VALUE);
        dp[n]=0;

        for(int i=n-1;i>=0;i--){
            int choiceB=Integer.MIN_VALUE;
            int choiceC=Integer.MIN_VALUE;
            int choiceA=stoneValue[i]-dp[i+1];
            if(i+1<n) choiceB=stoneValue[i]+stoneValue[i+1]-dp[i+2];
            if(i+2<n) choiceC=stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]-dp[i+3];
            dp[i]=Math.max(choiceA,Math.max(choiceB,choiceC));
        }
        int res=dp[0];  //game ends when no stones are available
        if(res>0){
            return "Alice";
        }else if(res==0){
            return "Tie";
        }else{
            return "Bob";
        }
    }
}

//memo

class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n=stoneValue.length;
        int[] dp=new int[n];
        Arrays.fill(dp,Integer.MIN_VALUE);
        int res=solve(0,stoneValue,dp);
        if(res>0){
            return "Alice";
        }else if(res==0){
            return "Tie";
        }else{
            return "Bob";
        }
    }
    public int solve(int i, int[] stoneValue,int[] dp){
        int n=stoneValue.length;
        if(i==n) return 0;
        if(dp[i]!=Integer.MIN_VALUE) return dp[i];
        int choiceB=Integer.MIN_VALUE;
        int choiceC=Integer.MIN_VALUE;
        int choiceA=stoneValue[i]-solve(i+1,stoneValue,dp);
      
        if(i+1<n) choiceB=stoneValue[i+1]+stoneValue[i]-solve(i+2,stoneValue,dp);
        
      
        if(i+2<n)choiceC=stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]-solve(i+3,stoneValue,dp);
        
        return dp[i]=Math.max(choiceA,Math.max(choiceB,choiceC));
    }
}