package StacksANDqueues;
import java.util.*;
class slidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0 || k<=0){
            return new int[]{0};
        }
        int n=nums.length;
        Deque<Integer> dq=new ArrayDeque<>();
        int[] ans=new int[n-k+1];

        for(int i=0;i<nums.length;i++){
            while(!dq.isEmpty() && dq.peekFirst()==i-k){
                dq.pollFirst();
            }

            while(!dq.isEmpty() && nums[dq.peekLast()]<nums[i]){
                dq.pollLast();
            }
            dq.offerLast(i);
            if(i>=k-1){
                ans[i-k+1]=nums[dq.peekFirst()];
            }
        }
        return ans;
    }
}