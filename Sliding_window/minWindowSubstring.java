package Sliding_window;
import java.util.*;

class minWindowSubstring {
    public String minWindow(String s, String t) {
        
        int n=s.length();
        int m=t.length();

        HashMap<Character,Integer> hp=new HashMap<>();
        for(char ch:t.toCharArray()){
            hp.put(ch,hp.getOrDefault(ch,0)+1);
        }

        int left=0;
        int need=hp.size();//remember the concept of countin strings sliding window pattern
        int sIdx=-1;
        int minLen=Integer.MAX_VALUE;
        String ans="";

        for(int right=0;right<n;right++){
            char ch=s.charAt(right);

            if(hp.containsKey(ch)){
                hp.put(ch,hp.get(ch)-1);
                if(hp.get(ch)==0) need--;   //remove the char needed
            }

            while(need==0){  //reduce the window till need==0 so we can get minLen
                sIdx=left;
                int len=right-left+1;
                if(minLen>len){
                    minLen=len;
                    ans=s.substring(sIdx,right+1);
                }
                char l=s.charAt(left);

                if(hp.containsKey(l)){
                    if(hp.get(l)==0) need++;   //restore the char needed in map
                    hp.put(l,hp.get(l)+1);
                }
                left++;
            }
        }
        return ans;
    }
}