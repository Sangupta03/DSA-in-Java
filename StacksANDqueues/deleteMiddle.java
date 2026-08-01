package StacksANDqueues;
import java.util.*;
class deleteMiddle {
    public void deleteMid(Stack<Integer> s) {
        int n=s.size();
        Stack<Integer> temp=new Stack<>();
        int midIdx=n-(n+1)/2;
        
        for(int i=0;i<=midIdx;i++){
            temp.add(s.pop());
        }
        temp.pop();
        
        while(!temp.isEmpty()){
            s.push(temp.pop());
        }
        
    }
}


//using recursion
class Solution {
    public void deleteMid(Stack<Integer> s) {
        int n=s.size();
        solve(s,n,0);
    }
    
    public void solve(Stack<Integer> s,int n,int cnt){
        if(cnt==n/2){
            //reached the middle element
            s.pop();
            return;
        }
        // Remove the current top element and hold it in the call stack
        int top=s.pop();
        solve(s,n,cnt+1); //Recurse to the next element
        s.push(top); // Push the held element back after the middle is deleted
    }
}