class twistedMirrorPath {
    //dir = 0 → we entered while moving RIGHT
    //dir = 1 → we entered while moving DOWN

    public int MOD=1_000_000_007;
    public int uniquePaths(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        if(n == 1 && m == 1) return 1; //edge case 1*1 matrix

        int[][][] dp=new int[n][m][2];

        for(int[][] twoD:dp){
            for(int[] oneD: twoD){
                Arrays.fill(oneD,-1);
            }
        }
        int cnt1=solve(1,0,1,grid,dp); //move down
        int cnt2=solve(0,1,0,grid,dp); //move right

        return (cnt1+cnt2)%MOD;
    }
    //we have an extra state here because for mirror we need to know which direction we came from

    public int solve(int i,int j,int dir,int[][] grid,int[][][] dp){

        int n=grid.length;
        int m=grid[0].length;

        if(i==n-1 && j==m-1) return 1;
        if(i>=grid.length || j>=grid[0].length) return 0;
        
        if(dp[i][j][dir]!=-1) return dp[i][j][dir];

        if(grid[i][j]==0){
            dp[i][j][dir]=(solve(i+1,j,1,grid,dp)+solve(i,j+1,0,grid,dp))%MOD;
        }else{
            if(dir==0){
                // Entered while moving RIGHT
                // So mirror turns us DOWN
                dp[i][j][dir]=solve(i+1,j,1,grid,dp);
            }else{
                dp[i][j][dir]=solve(i,j+1,0,grid,dp);
            }
        }

        return (dp[i][j][dir])%MOD;
    }
}