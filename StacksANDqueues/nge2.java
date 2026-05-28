package StacksANDqueues;
import java.util.*;

class nge2 {
    public int[] nextGreaterElements(int[] nums) {
        int[] ans=new int[nums.length];

        Stack<Integer> stk=new Stack<>();
        int n=nums.length;
        for(int i=2*n-1;i>=0;i--){
            while(!stk.isEmpty() && stk.peek()<=nums[i%n]){
                stk.pop();
            }
            
            if(i<n){
                ans[i]=stk.isEmpty()?-1:stk.peek();
            }
            stk.push(nums[i%n]);
        }
        return ans;
    }
}
