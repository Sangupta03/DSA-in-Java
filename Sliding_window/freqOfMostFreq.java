package Sliding_window;
import java.util.*;

class freqOfMostFreq {
    public int maxFrequency(int[] nums, int k) {
        int maxFreq=1;
        long total=0;
        Arrays.sort(nums);
        int left=0;

        for(int right=0;right<nums.length;right++){
            total+=nums[right];

            while((long)(right-left+1)*nums[right]-total>k){
                total-=nums[left];
                left++;
            }
            maxFreq=Math.max(maxFreq,(right-left+1));
        }
        return maxFreq;
    }
}