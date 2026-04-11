class cntSubsetsWithSum {
    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
        // code here
        int n=nums.length;
        int[][] dp=new int[n][target+1];
        
        
        dp[0][0]=(nums[0]==0)?2:1;
        
        if(nums[0]!=0 && nums[0]<=target){
            dp[0][nums[0]]=1;
        }
        
        for(int idx=1;idx<n;idx++){
            for(int sum=0;sum<=target;sum++){
                int notTake=dp[idx-1][sum];
                int take=0;
                
                if(nums[idx]<=sum){
                    take=dp[idx-1][sum-nums[idx]];
                }
                dp[idx][sum]=take+notTake;
                
            }
        }
        return dp[n-1][target];
    }
}
