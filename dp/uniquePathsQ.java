public class uniquePathsQ {
    public int uniquePaths(int m, int n) {
        int[][] dp=new int[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int left=0;
                int up=0;
                if(i==0 && j==0){
                    dp[i][j]=1;
                    continue;
                }
                if(i>0){
                    left=dp[i-1][j];
                }if(j>0){
                    up=dp[i][j-1];
                }
                dp[i][j]=left+up; 
            }
            
        }
        return dp[m-1][n-1];
    } 
  
}
