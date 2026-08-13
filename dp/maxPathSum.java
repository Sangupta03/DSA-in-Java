import java.util.*;
class maxPathSum {
    public int maximumPath(int[][] mat) {
        // code here
        int n=mat.length;
        int m=mat[0].length;
        
        int[][] dp=new int[n][m];
        
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        int maxi=0;
        for(int i=0;i<m;i++){
            int ans=solve(0,i,mat,dp);
            maxi=Math.max(maxi,ans);
        }
        return maxi;
    }
    
    public int solve(int row,int col,int[][] mat,int[][] dp){
        int n=mat.length;
        int m=mat[0].length;
        
        if(row<0 || col<0 || row>=n || col>=m){
            return (int)-1e9;
        }
        if(row==n-1){
            return mat[row][col];
        }
        if(dp[row][col]!=-1) return dp[row][col];
        
        int down=mat[row][col]+solve(row+1,col,mat,dp);
        int left=mat[row][col]+solve(row+1,col-1,mat,dp);
        int right=mat[row][col]+solve(row+1,col+1,mat,dp);
        
        return dp[row][col]=Math.max(down,Math.max(left,right));
    }
}
