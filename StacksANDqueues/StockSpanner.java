package StacksANDqueues;
import java.util.*;
class Value{
    int idx;
    int val;
    Value(int idx,int val){
        this.idx=idx;
        this.val=val;
    }
}
class StockSpanner {

    public Stack<Value> stk;
    public int idx;
    public StockSpanner() {
     stk=new Stack<>();
     idx=-1;  
    }
    
    public int next(int price) {
        idx++;
        int span;

        while(!stk.isEmpty() && stk.peek().val<=price){
            stk.pop();
        }

        if(stk.isEmpty()){
            span=idx+1;
        }else{
            span=idx-stk.peek().idx;
        }
        stk.push(new Value(idx,price));
        return span;
    }
}