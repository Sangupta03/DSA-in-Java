package Heaps;
import java.util.*;

class reorganiseString {
    public String reorganizeString(String s) {
        
        int[] freq=new int[26];
        int maxFreq=0;
        int n=s.length();

        for(char c:s.toCharArray()){
            freq[c-'a']++;
            maxFreq=Math.max(maxFreq,freq[c-'a']);
        }

        if(maxFreq>(n+1)/2) return "";

        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->(b[1]-a[1]));

        for(int i=0;i<26;i++){
            if(freq[i]>0){
                pq.offer(new int[]{i,freq[i]});
            }
        }

        StringBuilder sb=new StringBuilder();
        int[] prev=null;

        while(!pq.isEmpty()){
            int[] curr=pq.poll();

            sb.append((char)(curr[0]+'a'));

            curr[1]--;
            
            if(prev!=null){
                pq.offer(prev);
            }

            if(curr[1]>0){
                prev=curr;
            }else{
                prev=null;
            }
        }
        return sb.toString();
    }
}