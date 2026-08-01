package StacksANDqueues;
import java.util.*;
class reverseStackRecusive {
    public static void reverseStack(Stack<Integer> st) {
        if(st.size()<=0) return;
        int top=st.pop();
        reverseStack(st);
        insertAtBottom(st,top);
    }
    
    public static void insertAtBottom(Stack<Integer> st,int x){
        if(st.isEmpty()){
            st.push(x);
            return;
        }
        
        int top=st.pop();
        insertAtBottom(st,x);
        st.push(top);
    }
}
