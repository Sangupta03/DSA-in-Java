import java.util.*;
class jumpGame6 {
  //tabulation
    public int maxResult(int[] nums, int k) {
        int n=nums.length;
        int[] dp=new int[n];

        dp[0]=nums[0];

        for(int idx=1;idx<n;idx++){
            dp[idx]=Integer.MIN_VALUE;
            for(int i=1;i<=k;i++){
                if(idx-i>=0){
                    dp[idx]=Math.max(dp[idx],nums[idx]+dp[idx-i]);
                }
            }
        }
        return dp[n-1];
    }
//memo
    public int solve(int idx,int[] nums,int k,int[] dp){
        if(idx==0) return nums[0];
        if(idx<0) return 0;

        if(dp[idx]!=-1) return dp[idx];
        dp[idx]=Integer.MIN_VALUE;
        for(int i=1;i<=k;i++){
            if(idx-i>=0){
                dp[idx]=Math.max(dp[idx],nums[idx]+solve(idx-i,nums,k,dp));
            }
        }
        return dp[idx];
    }
}


//OPTIMAL APPROACH SAME LIKE SLIDING WINDOW MAXIMUM, MAINTAIN DECREASING MONOTONIC QUEUE OF MAX DP VALUES, AND SLIDE WINDOW

class Solution {
    public int maxResult(int[] nums, int k) {

        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];

        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(0);   // store indices

        for (int i = 1; i < n; i++) {

            // Remove indices outside the window
            while (!dq.isEmpty() && dq.peekFirst() < i - k) {
                dq.pollFirst();
            }

            // Best previous score
            dp[i] = nums[i] + dp[dq.peekFirst()];

            // Maintain decreasing dp values
            while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);
        }

        return dp[n - 1];
    }
}

