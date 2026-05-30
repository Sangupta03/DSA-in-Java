package StacksANDqueues;
import java.util.*;
class sumOfSubRanges {
    public long subArrayRanges(int[] nums) {
        return (findMaximums(nums)-findMinimums(nums));
    }

    public long findMinimums(int[] nums){

        int n=nums.length;
        int[] nse=new int[n];
        int[] pse=new int[n];
        Stack<Integer> stk=new Stack<>();

        for(int i=n-1;i>=0;i--){
            while(!stk.isEmpty() && nums[stk.peek()]>=nums[i]){
                stk.pop();
            }
            nse[i]=stk.isEmpty()?n:stk.peek();
            stk.push(i);
        }
        stk.clear();

        for(int i=0;i<n;i++){
            while(!stk.isEmpty() && nums[stk.peek()]>nums[i]){
                stk.pop();
            }
            pse[i]=stk.isEmpty()?-1:stk.peek();
            stk.push(i);
        }
        stk.clear();
        long total=0;

        for(int i=0;i<n;i++){
            long left=i-pse[i];
            long right=nse[i]-i;
            total=total+(left*right*nums[i]); //contribution
        }
        return total;
    }
    public long findMaximums(int[] nums){

        int n=nums.length;
        int[] nge=new int[n];
        int[] pge=new int[n];
        Stack<Integer> stk=new Stack<>();

        for(int i=n-1;i>=0;i--){
            while(!stk.isEmpty() && nums[stk.peek()]<=nums[i]){
                stk.pop();
            }
            nge[i]=stk.isEmpty()?n:stk.peek();
            stk.push(i);
        }
        stk.clear();

        for(int i=0;i<n;i++){
            while(!stk.isEmpty() && nums[stk.peek()]<nums[i]){
                stk.pop();
            }
            pge[i]=stk.isEmpty()?-1:stk.peek();
            stk.push(i);
        }
        stk.clear();
        long total=0;

        for(int i=0;i<n;i++){
            long left=i-pge[i];
            long right=nge[i]-i;
            total=total+(left*right*nums[i]); //contribution
        }
        return total;
    }
}
