import java.util.*;
//variable start and ending points here;
class minPathFallSum {
    public int minFallingPathSum(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;

        int[][] dp=new int[m][n];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }

        int mini=Integer.MAX_VALUE;
        for(int j=0;j<n;j++){
            mini=Math.min(mini,solve(m-1,j,matrix,dp));
        }
        return mini;
    }

    public int solve(int i,int j,int[][] matrix,int[][] dp){


        if(j<0 || j>=matrix[0].length) return (int) 1e9;
        if(i==0) return matrix[0][j];
        if(dp[i][j]!=-1) return dp[i][j];
        int ldg=matrix[i][j]+solve(i-1,j-1,matrix,dp);
        int up=matrix[i][j]+solve(i-1,j,matrix,dp);
        int rdg=matrix[i][j]+solve(i-1,j+1,matrix,dp);

        return dp[i][j]=Math.min(ldg,Math.min(up,rdg));
    }
}