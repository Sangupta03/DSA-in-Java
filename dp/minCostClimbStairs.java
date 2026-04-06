import java.util.*;
class minCostClimbStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n=cost.length;
        return solve(n,cost);
    }

    public int solve(int idx,int[] cost){
        if(idx==0 || idx==1) return 0;

        return Math.min(cost[idx-1]+solve(idx-1,cost),cost[idx-2]+solve(idx-2,cost));
    }
}

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n=cost.length;
        int[] dp=new int[n+1];
        Arrays.fill(dp,-1);
        return solve(n,cost,dp);
    }

    public int solve(int idx,int[] cost,int[] dp){
        if(idx==0 || idx==1) return 0;
        if(dp[idx]!=-1) return dp[idx];
        return dp[idx]=Math.min(cost[idx-1]+solve(idx-1,cost,dp),cost[idx-2]+solve(idx-2,cost,dp));
    }
}


class Solution1 {
    public int minCostClimbingStairs(int[] cost) {
        int n=cost.length;
        int[] dp=new int[n+1];
        dp[0]=dp[1]=0;
        

        for(int i=2;i<=n;i++){
            dp[i]=Math.min(cost[i-1]+dp[i-1],cost[i-2]+dp[i-2]);
        }
        return dp[n];
    }
}

class Solution2 {
    public int minCostClimbingStairs(int[] cost) {
        int n=cost.length;
    
        int prev1=0;
        int prev2=0;

        for(int i=2;i<=n;i++){
            int curr=Math.min(cost[i-1]+prev1,cost[i-2]+prev2);
            prev2=prev1;
            prev1=curr;
        }
        return prev1;
    }
}
