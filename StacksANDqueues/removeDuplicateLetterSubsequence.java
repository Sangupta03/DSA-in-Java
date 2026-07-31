package StacksANDqueues;
import java.util.*;
class removeDuplicateLetterSubsequence {
    public String smallestSubsequence(String s) {
        int[] freq=new int[26];

        for(char ch:s.toCharArray()){
            freq[ch-'a']++;
        }

        boolean[] visited=new boolean[26];
        Deque<Character> stk=new ArrayDeque<>();

        for(char ch:s.toCharArray()){
            freq[ch-'a']--;  //to process each character
            if(visited[ch-'a']) continue; //already visited no need to process again
            while(!stk.isEmpty() && stk.peek()>ch && freq[stk.peek()-'a']>0){  //similar to pge
                int top=stk.pop();
                visited[top-'a']=false;  //mark as unvisited
            }
            stk.push(ch);  
            visited[ch-'a']=true;
        }
        StringBuilder sb=new StringBuilder();
        while(!stk.isEmpty()){
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }
}