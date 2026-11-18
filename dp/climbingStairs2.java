import java.util.*;

//memo
//"What is the minimum cost from this step?"
class climbingStairs2{
    public int climbStairs(int n, int[] costs) {
        int[] dp=new int[n+1];
        Arrays.fill(dp,-1);
        return solve(0,costs,dp,n);
    }

    public int solve(int steps,int[] costs,int[] dp,int n){

        if(steps==n){
            return 0;
        }

        if(dp[steps]!=-1) return dp[steps];
        int ans=Integer.MAX_VALUE;

        for(int jumps=1;jumps<=3;jumps++){
            int next=steps+jumps;

            if(next<=n){
                int cost=costs[next-1]+jumps*jumps; //-1 since 1 indexed array
                //next-steps=jumps
                int remains=solve(next,costs,dp,n);

                ans=Math.min(ans,remains+cost);
            }
        }
        return dp[steps]=ans;
    }
}

//tabulation
//Let me calculate the answer for every step, starting from the destination and moving backwards

class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp=new int[n+1];
        dp[n]=0;

        for(int steps=n-1;steps>=0;steps--){
            //if(steps==n) dp[steps]=0;
            int ans=Integer.MAX_VALUE;

            for(int jumps=1;jumps<=3;jumps++){
                int next=steps+jumps;
                if(next<=n){
                    int cost=costs[next-1]+jumps*jumps;
                    int remains=dp[next];

                    ans=Math.min(ans,cost+remains);
                }
            }
            dp[steps]=ans;
        }
        return dp[0];
    }
  }