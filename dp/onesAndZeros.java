import java.util.*;
class onesAndZeros {
    public int findMaxForm(String[] strs, int m, int n) {
        int len=strs.length;
        int[][][] dp=new int[len][m+1][n+1];

        for(int[][] oned:dp){
            for(int[] twoD:oned){
                Arrays.fill(twoD,-1);
            }
        }

        return solve(len-1,m,n,strs,dp);
    }

    public int solve(int idx,int zeros,int ones,String[] strs,int[][][] dp){
        if(idx<0){
            return 0;
        }
        if(dp[idx][zeros][ones]!=-1) return dp[idx][zeros][ones];

        int notTake=solve(idx-1,zeros,ones,strs,dp);
        int take=Integer.MIN_VALUE;

        int val0=0;
        int val1=0;

        for(char ch:strs[idx].toCharArray()){
            if(ch=='0'){
                val0++;
            }else{
                val1++;
            }
        }
        if(val0<=zeros && val1<=ones){
            take=1+solve(idx-1,zeros-val0,ones-val1,strs,dp);
        }
        return dp[idx][zeros][ones]=Math.max(take,notTake);
    }

}

//ones and zeros with tabulation simple code
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // 2D DP table: dp[i][j] stores the max subset size for 'i' 0s and 'j' 1s
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            int zeros = 0;
            int ones = 0;
            
            // Count the 0s and 1s in the current string
            for (char ch : s.toCharArray()) {
                if (ch == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            // Iterate BACKWARDS to ensure we don't count the same string twice
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }

        return dp[m][n];
    }
}