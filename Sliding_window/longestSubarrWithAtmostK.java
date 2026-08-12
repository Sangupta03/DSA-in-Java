package Sliding_window;
import java.util.*;

class longestSubarrWithAtmostK {
    public int maxSubarrayLength(int[] nums, int k) {
        int maxLen=0;
        int left=0;

        int n=nums.length;
        HashMap<Integer,Integer> hp=new HashMap<>();

        for(int right=0;right<n;right++){
            hp.put(nums[right],hp.getOrDefault(nums[right],0)+1);

            while(hp.get(nums[right])>k){
                if(hp.get(nums[left])==0) hp.remove(nums[left]);
                hp.put(nums[left],hp.get(nums[left])-1);
                left++;
            }
            maxLen=Math.max(right-left+1,maxLen);
        }
        return maxLen;
    }
}