package StacksANDqueues;

import java.util.*;
class removeKadjDuplicates {
    public String removeDuplicates(String s, int k) {
        Deque<Pair> stk=new ArrayDeque<>();
        
        for(int i=0;i<s.length();i++){
            char curr=s.charAt(i);
            if(!stk.isEmpty() && stk.peek().ch==curr){
                Pair p=stk.peek();
                p.cnt++;
                if(p.cnt==k){
                    stk.pop();
                }
            }else{
                stk.push(new Pair(1,curr));
            }
        }
        StringBuilder sb=new StringBuilder();

        while(!stk.isEmpty()){
            Pair p=stk.pop();
            for(int i=0;i<p.cnt;i++){
                sb.append(p.ch);
            }
        }
        return sb.reverse().toString();
    }
}
class Pair{
    int cnt;
    char ch;
    Pair(int cnt,char ch){
        this.cnt=cnt;
        this.ch=ch;
    }
}
