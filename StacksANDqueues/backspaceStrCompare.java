package StacksANDqueues;
import java.util.*;
class backspaceStrCompare {
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stk1=new Stack<>();

        for(char c:s.toCharArray()){
            if(c!='#'){
                stk1.push(c);
            }else if(!stk1.isEmpty() && c=='#'){
                stk1.pop();
            }
        }
        StringBuilder sb1=new StringBuilder();
        while(!stk1.isEmpty()){
            sb1.append(stk1.pop());
        }
        Stack<Character> stk2=new Stack<>();

        for(char c:t.toCharArray()){
            if(c!='#'){
                stk2.push(c);
            }else if(!stk2.isEmpty() && c=='#'){
                stk2.pop();
            }
        }
        StringBuilder sb2=new StringBuilder();
        while(!stk2.isEmpty()){
            sb2.append(stk2.pop());
        }

        if(sb1.toString().equals(sb2.toString())){
            return true;
        }
        return false;
    }
}

//OPTIMAL APPROACH USING 2POINTERS


class Solution {

    public boolean backspaceCompare(String s, String t) {

        int i = s.length() - 1;
        int j = t.length() - 1;

        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {

            // Find next valid character in s
            while (i >= 0) {

                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                }
                else if (skipS > 0) {
                    skipS--;
                    i--;
                }
                else {
                    break;
                }
            }

            // Find next valid character in t
            while (j >= 0) {

                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                }
                else if (skipT > 0) {
                    skipT--;
                    j--;
                }
                else {
                    break;
                }
            }

            // Compare valid characters
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            }
            else if (i >= 0 || j >= 0) {
                return false;
            }

            i--;
            j--;
        }

        return true;
    }
}
