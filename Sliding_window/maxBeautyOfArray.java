package Sliding_window;
import java.util.*;
class Solution {
    public int maximumBeauty(int[] nums, int k) {
        
        int left=0;
        int maxLen=0;
        Arrays.sort(nums);
        //identify the problem as overlapping intervals
        //range is [nums[i]-k,nums[i]+k];
        //where they overlap will be the number we convert to Eg 4
        //(a-k,a+k) and (b-k,b+k) to make them equal use the formula below
        //overlapping intervals at nums[j]-nums[i]<=2k
        for(int right=0;right<nums.length;right++){

            while(nums[right]-nums[left]>2*k){
                left++;
            }
            maxLen=Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }
}