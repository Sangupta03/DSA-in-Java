import java.util.*;
class predictWinner {
    public boolean predictTheWinner(int[] nums) {
        int n=nums.length;
        int l=0;
        int r=n-1;
        int[][] dp=new int[n][n];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return solve(l,r,nums,dp)>=0;
    }

    public int solve(int i,int j,int[] nums,int[][] dp){
        if(i==j) return nums[i];
        if(dp[i][j]!=-1) return dp[i][j];

        //2 choices since curr player can either pick left or right
        int choiceA=nums[i]-solve(i+1,j,nums,dp); //max score diff b/w P1 and P2
        int choiceB=nums[j]-solve(i,j-1,nums,dp); //max score diff b/w P1 and P2
        return dp[i][j]=Math.max(choiceA,choiceB);
    }
}