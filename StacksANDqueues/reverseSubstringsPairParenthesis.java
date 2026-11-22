package StacksANDqueues;
import java.util.*;

class reverseSubstringsPairParenthesis {
    public String reverseParentheses(String s) {
        int n=s.length();
        Stack<Character> st=new Stack<>();
        
        for(int i=0;i<n;i++){
            char ch=s.charAt(i);

            if(ch==')'){
                StringBuilder sb=new StringBuilder();
                while(!st.isEmpty() && st.peek()!='('){
                    sb.append(st.pop());
                }
                st.pop(); //removes '('

                //for push reversed chars from sb into stack

                for(int j=0;j<sb.length();j++){
                    st.push(sb.charAt(j));
                }
            }else{
                st.push(ch);
            }
        }
        StringBuilder ans=new StringBuilder();
        while(!st.isEmpty()){
            ans.append(st.pop());
        }
        return ans.reverse().toString();
    }
}

