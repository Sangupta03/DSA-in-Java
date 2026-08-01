package StacksANDqueues;
import java.util.*;
class MinStack {
    Stack<Long> stk;
    long minEle;
    public MinStack() {
        stk=new Stack<>();
        minEle=Long.MAX_VALUE;
    }
    
    public void push(int value) {
        long val=value;
        if(stk.isEmpty()){
            minEle=val;
            stk.push(val);
        }else{
            if(val<minEle){
                stk.push(2L*val-minEle);
                minEle=val;
            }else{
                stk.push(val);
            }
        }
    }
    
    public void pop() {
        if(stk.isEmpty()){
            return;
        }else{
            long val=stk.pop();
            if(val<minEle){
                minEle=2L*minEle-val;
            }
        }
    }
    
    public int top() {
        if(stk.isEmpty()){
            return -1;
        }else{
            long val=stk.peek();
            if(val<minEle){
                return (int) minEle;
            }else{
                return (int) val;
            }
        }
    }
    
    public int getMin() {
        return (int) minEle;
    }
}
