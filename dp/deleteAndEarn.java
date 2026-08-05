import java.util.*;
class deleteAndEarnQ {
    public int deleteAndEarn(int[] nums) {
        int max=0;

        for(int x:nums){
            if(x>max){
                max=x;
            }
        }

        int[] points=new int[max+1];
        for(int num:nums){
            points[num]+=num;
        }
        int[] dp=new int[max+1];
        Arrays.fill(dp,-1);
        return solve(max,points,dp);
    }
    public int solve(int idx,int[] points,int[] dp){
        if(idx<0) return 0;
        if(idx==0) return points[0];

        int take=points[idx]+solve(idx-2,points,dp);
        int notTake=solve(idx-1,points,dp);

        return Math.max(take,notTake);
    }
}
