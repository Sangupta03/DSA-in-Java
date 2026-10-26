package Greedy;
import java.util.*;
class maxAlternatingSumOfSquares {
    public long maxAlternatingSum(int[] nums) {
        int n=nums.length;

        int[] res=new int[n];

        for(int i=0;i<n;i++){
            res[i]=nums[i]*nums[i];
        }

        Arrays.sort(res);
        long ans=0;


        for(int i=0;i<n/2;i++){
            ans-=res[i];
        }

        for(int i=n/2;i<n;i++){
            ans+=res[i];
        }

        return ans;
    }
}
