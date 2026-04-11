class subsetSum {
    public boolean isSubsetSum(int[] arr, int target) {
      int n=arr.length;
      boolean[][] dp=new boolean[n][target+1];

      for(int i=0;i<n;i++){
        dp[i][0]=true;
      }
      if(arr[0]<=target) dp[0][arr[0]]=true;

      for(int idx=1;idx<n;idx++){
        for(int sum=1;sum<=target;sum++){
            boolean notPick=dp[idx-1][sum];
            boolean pick=false;
            if(arr[idx]<=sum){
                pick=dp[idx-1][sum-arr[idx]];
            }
            dp[idx][sum]=pick||notPick;
        }
      }
     
      return dp[n-1][target];
    }
  }