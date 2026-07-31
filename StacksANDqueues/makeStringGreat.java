package StacksANDqueues;
import java.util.*;
class makeStringGreat {
    public String makeGood(String s) {
        Deque<Character> stk=new ArrayDeque<>();

        for(char ch:s.toCharArray()){
            if(!stk.isEmpty() && Character.toLowerCase(ch)==Character.toLowerCase(stk.peek()) && stk.peek()!=ch){
                stk.pop();
            }else{
                stk.push(ch);
            }
        }

        StringBuilder sb=new StringBuilder();
        while(!stk.isEmpty()){
            sb.append(stk.pop());
        }
        sb.reverse();
        return sb.toString().isEmpty()?"":sb.toString();
    }
}

//  METHOD 2;

class Solution {
    public String makeGood(String s) {
        Deque<Character> stk=new ArrayDeque<>();

        for(char ch:s.toCharArray()){
            if(!stk.isEmpty() && Math.abs(ch-stk.peek())==32){
                stk.pop();  //upper and lowercase characters in Ascii are exactly 32 chars apart
            }else{
                stk.push(ch);
            }
        }
        StringBuilder sb=new StringBuilder();
        while(!stk.isEmpty()){
            sb.append(stk.pop());
        }
        sb.reverse();
        return sb.toString().isEmpty()?"":sb.toString();
    }
}