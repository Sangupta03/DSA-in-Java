package Sliding_window;
import java.util.*;

class maximumErasureQ {
    public int maximumUniqueSubarray(int[] nums) {
        
        HashSet<Integer> hs=new HashSet<>();
        int left=0;
        int sum=0;
        int maxSum=0;
        int n=nums.length;

        for(int right=0;right<n;right++){

            while(hs.contains(nums[right])){
                sum-=nums[left];
                hs.remove(nums[left]);
                left++;
            }
            hs.add(nums[right]);
            sum+=nums[right];
            maxSum=Math.max(sum,maxSum);
        }
        return maxSum;
    }
}