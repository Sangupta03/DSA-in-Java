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

//approach 2

//help us store the characters to skip in result string to stack
//after skipping characters reverse the result

class Solution {
    public String reverseParentheses(String s) {
        int n=s.length();
        Stack<Integer> st=new Stack<>();

        StringBuilder result=new StringBuilder();
        
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);

            if(ch=='('){
                st.push(result.length()); //to skip these characters store them
            }else if(ch==')'){
                int l=st.pop(); //skip these char
                reverse(result,l,result.length()-1);
            }else{
                //simple character
                result.append(ch);
            }
        }

        return result.toString();
    }

    public void reverse(StringBuilder result, int l,int r){

        while(l<=r){
            char temp=result.charAt(l);
            result.setCharAt(l,result.charAt(r));
            result.setCharAt(r,temp);
            l++;
            r--;
        }
    }
}


//BEST APPROACH WORMHOLE APPROACH
//MOVE LEFT TO RIGHT;
//THEN RIGHT TO LEFT;

class Solution {
    //MOVE LTR THEN RTL
    // ad(bc)ef explains this movement 

    //zigzag wormhole approach

    public String reverseParentheses(String s) {
        
        HashMap<Integer,Integer> mp=new HashMap<>();
        //map open and close parenthesis pair

        Stack<Integer> stk=new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);

            if(ch=='('){
                stk.push(i);
            }else if(ch==')'){
                int l=stk.pop();
                mp.put(l,i);
                mp.put(i,l);
            }
        }

        int flag=1;
        
        StringBuilder res=new StringBuilder();

        for(int i=0;i<s.length();i+=flag){
            char ch=s.charAt(i);

            if(ch=='(' || ch==')'){
                flag=-flag;
                i=mp.get(i);
            }else{
                res.append(ch);
            }
        }

        return res.toString();
    }
}