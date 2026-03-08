package Sliding_window;
import java.util.*;
class LongestSubarrayDiffThanLimit {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> minQueue=new ArrayDeque<>();
        Deque<Integer> maxQueue=new ArrayDeque<>();
        int maxLen=0;
        int left=0;

        for(int right=0;right<nums.length;right++){

            while(!minQueue.isEmpty() && nums[minQueue.peekLast()]>nums[right]){
                minQueue.pollLast();
            }
            while(!maxQueue.isEmpty() && nums[maxQueue.peekLast()]<nums[right]){
                maxQueue.pollLast();
            }
            minQueue.add(right);
            maxQueue.add(right);

            while(nums[maxQueue.peekFirst()]-nums[minQueue.peekFirst()]>limit){
                if(maxQueue.peekFirst()==left){
                    maxQueue.pollFirst();
                }
                if(minQueue.peekFirst()==left){
                    minQueue.pollFirst();
                }
                left++;
            }
            maxLen=Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }
}
