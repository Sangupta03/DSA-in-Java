package StacksANDqueues;
import java.util.*;
class minStrLenAfterRemoval {
    public int minLength(String s) {
        Deque<Character> stk=new ArrayDeque<>();

        for(char ch:s.toCharArray()){
            if(!stk.isEmpty() && ch=='B' && stk.peek()=='A'){
                stk.pop();
            }else if(!stk.isEmpty() && ch=='D' && stk.peek()=='C'){
                stk.pop();
            }else{
                stk.push(ch);
            }
        }
        return stk.size();
    }
}
