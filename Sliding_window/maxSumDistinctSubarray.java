package Sliding_window;

import java.util.*;
class maxSumDistinctSubarray {
    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum=0;
        long currSum=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        int left=0;
        
        for(int right=0;right<nums.length;right++){
            map.put(nums[right],map.getOrDefault(nums[right],0)+1);
            currSum+=nums[right];

            if(right-left+1>k){
                map.put(nums[left],map.get(nums[left])-1);
                if(map.get(nums[left])==0) map.remove(nums[left]);
                currSum-=nums[left];
                left++;
            }
            
            if(map.size()==k && right-left+1==k) maxSum=Math.max(currSum,maxSum);
        }
        return maxSum;

    }
}