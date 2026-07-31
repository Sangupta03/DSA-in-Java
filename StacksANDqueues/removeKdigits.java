package StacksANDqueues;
import java.util.*;
class removeKdigits {
    public String removeKdigitsQ(String num, int k) {
     
        Deque<Character> stk=new ArrayDeque<>();

        for(char c:num.toCharArray()){
            while(!stk.isEmpty() && k>0 && c<stk.peek()){
                stk.pop();
                k--;
            }
            stk.push(c);
        }

        while(!stk.isEmpty() && k>0){
            stk.pop();
            k--;
        }
        StringBuilder sb=new StringBuilder();
        while(!stk.isEmpty()){
            sb.append(stk.pop());
        }

        sb.reverse();
        int frontCh=0;

        for(int i=0;i<sb.length();i++){
            if(sb.charAt(frontCh)=='0'){
                frontCh++;
            }
        }
        return frontCh==sb.length()?"0":sb.substring(frontCh).toString();
    }
}