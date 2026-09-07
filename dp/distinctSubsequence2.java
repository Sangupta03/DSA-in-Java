import java.util.*;
class Solution {
    public int distinctSubseqII(String s) {
        int n=s.length();
        int MOD=1_000_000_007;

        long[] dp=new long[n+1];
        int[] last=new int[26];  //stores and tracks duplicate characters

        Arrays.fill(last,-1);
        dp[0]=1;

        for(int i=1;i<=n;i++){
            char ch=s.charAt(i-1);

            dp[i]=(2*dp[i-1]+MOD)%MOD;  //take+notTake formula to calulate subsequence at idx i

            if(last[ch-'a']!=-1){
                int prev=last[ch-'a'];
                dp[i]=(dp[i]-dp[prev]+MOD)%MOD;  //if duplicate exists remove it
            }
            last[ch-'a']=i-1;
        }
        return (int)(dp[n]-1+MOD)%MOD;  //-1 removes empty subsequence
    }
}