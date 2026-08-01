package StacksANDqueues;
import java.util.*;

class minAddToMakeValid {
    public String minRemoveToMakeValidQ(String s) {
        Deque<Integer> stk=new ArrayDeque<>();
        boolean[] visited=new boolean[s.length()];

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                stk.push(i);
            }else if(ch==')'){
                if(!stk.isEmpty()){
                    stk.pop();
                }else{
                    visited[i]=true;
                }
            }
        }

        while(!stk.isEmpty()){
            visited[stk.pop()]=true;
        }

        StringBuilder sb=new StringBuilder();

        for(int i=0;i<s.length();i++){
            if(!visited[i]){
                sb.append(s.charAt(i));
            }
        }
        
        return sb.toString();
    }
}
