package StacksANDqueues;
import java.util.*;

class insertAtBottom {
    public Stack<Integer> insertAtBottomQ(Stack<Integer> st, int x) {
        // recursive way //also try recursive stk reversal problem
        if(st.isEmpty()){
            st.push(x);
            return st;
        }
        
        int top=st.pop();
        insertAtBottomQ(st,x);
        st.push(top);
        return st;
    }
}

//another way is to use temp stack

class Solution {
    public Stack<Integer> insertAtBottom(Stack<Integer> st, int x) {
       
        Stack<Integer> temp=new Stack<>();
        
        while(!st.isEmpty()){
            temp.push(st.pop());
        }
        
        st.push(x);
        
        while(!temp.isEmpty()){
            st.push(temp.pop());
        }
        return st;
    }
}