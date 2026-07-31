package StacksANDqueues;
import java.util.*;
class longestValidParentheses {
    public int longestValidParenthesesQ(String s) {
        int maxi=0;
        Deque<Integer> stk=new ArrayDeque<>();
        stk.push(-1);  //put initial idx to calculate len
        int ans=0;

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                stk.push(i);  //push idx of char
            }else{
                stk.pop();   //try to find matching '(' and ')'
                if(stk.isEmpty()){   //no match found
                    stk.push(i);
                }else{
                    ans=i-stk.peek();  //match found track len
                }
            }
            maxi=Math.max(maxi,ans);
        }
        return maxi;
    }
}