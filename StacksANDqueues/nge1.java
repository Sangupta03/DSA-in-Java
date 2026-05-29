package StacksANDqueues;
import java.util.*;
class nge1{
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hp=new HashMap<>();
        Stack<Integer> stk=new Stack<>();
        int[] ans=new int[nums1.length];

        for(int x:nums2){
            while(!stk.isEmpty() && stk.peek()<x){
                hp.put(stk.pop(),x);
            }
            stk.push(x);
        }

        while(!stk.isEmpty()){
            hp.put(stk.pop(),-1);
        }

        int i=0;
        for(int y:nums1){
            ans[i++]=hp.get(y);
        }
        return ans;
    }
}
