import java.util.*;
class booleanParenthesization {
    static int countWays(String s) {

        int n=s.length();
        int[][][] dp=new int[n][n][2];
        for (int[][] arr2D : dp) {
            for (int[] arr1D : arr2D) {
                Arrays.fill(arr1D, -1);
            }
        }
        
        return solve(0,n-1,s,dp,1); //0 false, //1 true
    }
    
    static int solve(int i,int j,String s,int[][][] dp,int isTrue){
        int n=s.length();
        if(i>j) return 0;
        
        if(i==j){
            if(isTrue==1){
                return s.charAt(i)=='T'?1:0;
            }else{
                return s.charAt(i)=='F'?1:0;
            }
        }
        
        if(dp[i][j][isTrue]!=-1) return dp[i][j][isTrue];
        int ways=0;
        
        for(int k=i+1;k<=j-1;k=k+2){
            int lT=solve(i,k-1,s,dp,1);
            int rT=solve(k+1,j,s,dp,1);
            int lF=solve(i,k-1,s,dp,0);
            int rF=solve(k+1,j,s,dp,0);
            
            if(s.charAt(k)=='&'){
                if(isTrue==1){
                    ways+=lT*rT;
                }else{
                    ways+=(lF*rT)+(lT*rF)+(lF*rF);
                }
            }else if(s.charAt(k)=='|'){
                if(isTrue==1){
                    ways+=(lF*rT)+(lT*rF)+(lT*rT);
                }else{
                    ways+=(lF*rF);
                }
            }else if(s.charAt(k)=='^'){
                if(isTrue==1){
                    ways+=(lF*rT)+(lT*rF);
                }else{
                    ways+=(lF*rF)+(lT*rT);
                }
            }
        }
        return dp[i][j][isTrue]=ways;
    }
}