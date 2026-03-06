package Sliding_window;

import java.util.*;
class continuousSubarraysQ {
    public long continuousSubarrays(int[] nums) {
        
        Deque<Integer> maxQueue=new ArrayDeque<>();
        Deque<Integer> minQueue=new ArrayDeque<>();
        int n=nums.length;
        int left=0;
        long total=0;
        for(int right=0;right<n;right++){

            while(!maxQueue.isEmpty() && nums[maxQueue.peekLast()]<nums[right]){
                maxQueue.pollLast();
            }
            while(!minQueue.isEmpty() && nums[minQueue.peekLast()]>nums[right]){
                minQueue.pollLast();
            }
            maxQueue.add(right);
            minQueue.add(right);

            while(nums[maxQueue.peekFirst()]-nums[minQueue.peekFirst()]>2){
                if(maxQueue.peekFirst()==left){
                    maxQueue.pollFirst();
                }
                if(minQueue.peekFirst()==left){
                    minQueue.pollFirst();
                }
                left++;
            }
            total+=right-left+1;
        }
        return total;
    }
}
