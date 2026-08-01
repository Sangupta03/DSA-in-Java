package StacksANDqueues;
import java.util.*;

class CustomStack {
    ArrayList<Integer> arr;
    int maxSize;
    public CustomStack(int maxSize) {
        arr=new ArrayList<>();
        this.maxSize=maxSize;
    }
    
    public void push(int x) {
        if(maxSize==arr.size()) return;
        arr.add(x);
    }
    
    public int pop() {
        if(arr.size()==0) return -1;
        int x=arr.get(arr.size()-1);
        arr.remove(arr.size()-1);
        return x;
    }
    
    public void increment(int k, int val) {
        int idx=0;
        int limit=Math.min(k,arr.size());

        while(idx<limit){
            int x=arr.get(idx)+val;
            arr.set(idx, x);
            idx++;
        }
    }
}

//Custom Stack Optimal Approach using lazy propagation

class CustomStack1 {
    int maxSize;
    int top;
    int[] stk;
    int[] inc; //to store increment
    public CustomStack1(int maxSize) {
        top=-1;
        this.maxSize=maxSize;
        stk=new int[maxSize];
        inc=new int[maxSize];
    }
    
    public void push(int x) {
        if(top==maxSize-1) return;
        top++;
        stk[top]=x;
    }
    
    public int pop() {
        if(top==-1) return -1;
        int ans=stk[top]+inc[top];

        if(top>0) inc[top-1]+=inc[top];  //pass on to previous k bottom ones
        inc[top]=0;  //free from inc
        top--;
        return ans;
    }
    
    public void increment(int k, int val) {
        int idx=Math.min(k-1,top);
        if(idx>=0){
            inc[idx]+=val;  //store pending inc;
        }
    }
}
