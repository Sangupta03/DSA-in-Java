package StacksANDqueues;
import java.util.*;
class removeDuplicatesChar {
    public String removeDuplicates(String s) {
        Stack<Character> stk=new Stack<>();
        
        for(char c:s.toCharArray()){
            if(!stk.isEmpty() && c==stk.peek()){
                stk.pop();
            }else{
                stk.push(c);
            }
        }
        StringBuilder sb=new StringBuilder();
        while(!stk.isEmpty()){
            sb.append(stk.pop());
        }
        sb.reverse();
        return sb.toString();
    }
}

//Optimal treat stringbuilder as a stack
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb=new StringBuilder();
        

        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            int lastIdx=sb.length()-1;
            if(sb.length()>0 && sb.charAt(lastIdx)==c){
                sb.deleteCharAt(lastIdx);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
