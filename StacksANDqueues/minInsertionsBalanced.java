package StacksANDqueues;
import java.util.*;
class minInsertionsBalanced {
    public int minInsertions(String s) {
        ArrayDeque<Character> stk = new ArrayDeque<>();
        int Rcnt = 0;
        int ans = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // If we have an unmatched single ')' before an '(', we must close it with an added ')'
                if (Rcnt == 1) {
                    ans++; // Add missing ')'
                    if (!stk.isEmpty()) {
                        stk.pop(); // Matched with an existing '('
                    } else {
                        ans++; // No '(' existed, so add missing '('
                    }
                    Rcnt = 0;
                }
                stk.push(ch);
            } else { // ch == ')'
                Rcnt++;
                if (Rcnt == 2) {
                    if (!stk.isEmpty()) {
                        stk.pop(); // Matched "))" with '(' from stack
                    } else {
                        ans++; // Needed an opening '(' before "))"
                    }
                    Rcnt = 0;
                }
            }
        }

        // Handle trailing single ')'
        if (Rcnt == 1) {
            ans++; // Add missing ')'
            if (!stk.isEmpty()) {
                stk.pop();
            } else {
                ans++; // Add missing '('
            }
        }

        // Any remaining '(' on stack need two ')' each
        ans += stk.size() * 2;

        return ans;
    }
}


//OPTIMAL APPROACH USING CNT VARIABLE ONLY
//TIME O(N)
//SPACE O(1)
class Solution {
    public int minInsertions(String s) {
        int sCnt=0;  //using sCnt instead of stk for '(' tracking
        int Rcnt=0;
        int ans=0;

        for(char ch:s.toCharArray()){
            if(ch=='('){
                if(Rcnt==1){
                    ans++;  // ')' added
                    if(sCnt>0){
                        sCnt--;
                    }else{
                        ans++;  //rem '('
                    }
                    Rcnt=0;
                }
                sCnt++;
            }
            else{
                Rcnt++;
                if(Rcnt==2){
                    if(sCnt>0){
                        sCnt--;
                    }else{
                        ans++;  //for '(' char rem
                    }
                    Rcnt=0;
                }
            }
        }
        if(Rcnt==1){
            ans++;
            if(sCnt>0){
                sCnt--;
            }else{
                ans++;
            }
        }

        ans+=2*sCnt;
        return ans;
    }
}