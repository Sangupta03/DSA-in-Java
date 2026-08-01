package StacksANDqueues;
import java.util.*;

//O(1) time and space complexity
class SpecialMaxStack {
    long maxEle;
    Stack<Long> stk;
    public SpecialMaxStack() {
        // Define Stack
        maxEle=0L;
        stk=new Stack<>();
    }

    public void push(int x) {
        // Add an element to the top of Stack
        if(stk.isEmpty()){
            maxEle=x;
            stk.push((long)x);
        }else{
            if(x>maxEle){
                // Push the flagged value and update max
                stk.push(2L*x-maxEle);
                maxEle=x;
            }else{
                stk.push((long)x);
            }
        }
    }

    public void pop() {
        // Remove the top element from the Stack
        if(stk.isEmpty()){
            return;
        }else{
            long t=stk.pop();
            if(t>maxEle){
                // Restore the previous maximum
                maxEle=2L*maxEle-t;
            }
        }
    }

    public int peek() {
        // Returns top element of the Stack
        if(stk.isEmpty()) return -1;
        long t = stk.peek();
        if(t>maxEle){
            // If the value is a flag, the actual value is maxEle
            return (int)maxEle;
        }else{
            return (int)t;
        }
    }

    boolean isEmpty() {
        // Check if the stack is empty
        if(stk.size()==0) return true;
        return false;
    }

    public int getMax() {
        // Finds maximum element of Stack
        if(stk.isEmpty()) return -1;
        return (int) maxEle;
    }
}

