class classicknapsack {
    public int knapsack(int W, int val[], int wt[]) {
        // code here
        int n=val.length;
        
        int[][] dp=new int[n][W+1];
        
        for(int i=0;i<=W;i++){
            dp[0][i]=(wt[0]<=i)?val[0]:0;
        }
        
        for(int idx=1;idx<n;idx++){
            for(int w=0;w<=W;w++){
                int notTake=dp[idx-1][w];
                int take=0;
                if(wt[idx]<=w) take=val[idx]+dp[idx-1][w-wt[idx]];
                
                dp[idx][w]=Math.max(take,notTake);
            }
        }
        return dp[n-1][W];
    }
}
