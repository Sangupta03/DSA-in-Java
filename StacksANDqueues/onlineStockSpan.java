package StacksANDqueues;
import java.util.*;
class OnlineStockSpan {

    public Stack<Pair> stk;
   
    public OnlineStockSpan() {
        stk=new Stack<>();
        
    }
    
    public int next(int price) {
        int span=1;
        while(!stk.isEmpty() && stk.peek().price<=price){
            span+=stk.pop().span;
        }
        stk.push(new Pair(price,span));
        return span;
    }
}
class Pair{
    int price;
    int span;
    Pair(int price,int span){
        this.span=span;
        this.price=price;
    }
}
