class stoneGame2 {
    public int stoneGameII(int[] piles) {
        int total=0;
        for(int i=0;i<piles.length;i++){
            total+=piles[i];
        }
        int n=piles.length;
        int[][] dp=new int[n+1][n+1];

        for(int idx=n-1;idx>=0;idx--){
            for(int m=1;m<=n;m++){
                int sum=0;
                dp[idx][m]=Integer.MIN_VALUE;
                for(int X=1;X<=2*m;X++){
                    if(idx+X>n) break;
                    sum+=piles[X+idx-1];
                    int nextM=Math.min(Math.max(X,m),n); //to guard against going out of bounds
                    dp[idx][m]=Math.max(dp[idx][m],sum-dp[idx+X][nextM]);
                }
            }
        }

        int diff=dp[0][1];
        return (total+diff)/2;
    }

    public int solve(int idx,int m,int[] piles,int[][] dp){

        if(idx==piles.length) return 0;
        if(dp[idx][m]!=-1) return dp[idx][m];
        dp[idx][m]=Integer.MIN_VALUE;
        int sum=piles[idx];
        for(int X=1;X<=2*m;X++){
            if(idx+X<=piles.length){
                dp[idx][m]=Math.max(dp[idx][m],sum-solve(idx+X,Math.max(X,m),piles,dp));
                if(idx+X<piles.length) sum+=piles[idx+X];
            } 
            else break;
        }
        return dp[idx][m];
    }
}