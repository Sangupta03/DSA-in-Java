package StacksANDqueues;
import java.util.*;
class longestValidParentheses {
    public int longestValidParenthesesQ(String s) {
        int maxi=0;
        Deque<Integer> stk=new ArrayDeque<>();
        stk.push(-1);
        int ans=0;

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                stk.push(i);
            }else{
                stk.pop();
                if(stk.isEmpty()){
                    stk.push(i);
                }else{
                    ans=i-stk.peek();
                }
            }
            maxi=Math.max(maxi,ans);
        }
        return maxi;
    }
}