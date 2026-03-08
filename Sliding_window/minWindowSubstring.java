package Sliding_window;
import java.util.*;
class minWindowSubstring {
    public String minWindow(String s, String t) {
        int sIdx=-1;
        int cnt=0;
        HashMap<Character,Integer> hp=new HashMap<>();
        int left=0;
        int n=s.length();
        int m=t.length();
        int minLen=Integer.MAX_VALUE;

        if(t==null || m==0 || s==null) return "";

        for(int i=0;i<m;i++){
            hp.put(t.charAt(i),hp.getOrDefault(t.charAt(i),0)+1);
        }

        for(int right=0;right<n;right++){
            char r=s.charAt(right);
            if(hp.containsKey(r)){
                hp.put(r,hp.get(r)-1);
                if(hp.get(r)>=0){ //valid character else -1
                    cnt++;
                }
            }
            while(cnt==m){
                char leftch=s.charAt(left);
                if(minLen>right-left+1){
                    minLen=right-left+1;
                    sIdx=left;
                }
                if(hp.containsKey(leftch)){
                    hp.put(leftch,hp.get(leftch)+1); //to remove -1/-2 extra character
                    if(hp.get(leftch)>0){ //valid character removed due to left
                        cnt--;
                    }
                }
                left++;
            }
        }
        if(sIdx==-1) return "";
        else return s.substring(sIdx,sIdx+minLen);
    }
}