package StacksANDqueues;
import java.util.*;
class scoreOfParentheses {
    public int scoreOfParenthesesQ(String s) {
        Deque<Integer> stk=new ArrayDeque<>();

        int score=0;

        for(char ch:s.toCharArray()){
            if(ch=='(') stk.push(-1);
            else{
                if(stk.peek()==-1){ //case ()
                    stk.pop();
                    stk.push(1);
                }else{
                    int sum=0;
                    while(!stk.isEmpty() && stk.peek()!=-1){//case A+B
                        sum+=stk.pop();
                    }
                    stk.pop();  //pop last -1
                    stk.push(sum*2); //(A);
                }
            }
        }

        while(!stk.isEmpty()){
            score+=stk.pop();
        }
        return score;
    }
}
