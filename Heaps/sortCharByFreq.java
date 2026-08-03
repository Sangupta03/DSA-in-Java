package Heaps;
import java.util.*;
class sortCharByFreq {
    public String frequencySort(String s) {
        HashMap<Character,Integer> hp=new HashMap<>();

        for(char ch:s.toCharArray()){
            hp.put(ch,hp.getOrDefault(ch,0)+1);
        }

        PriorityQueue<Character> pq=new PriorityQueue<>((a,b)->hp.get(b)-hp.get(a));

        StringBuilder sb=new StringBuilder();

        for(char ch:hp.keySet()){
            pq.offer(ch);
        }

        while(!pq.isEmpty()){
            
            char ch=pq.poll();
            int freq=hp.get(ch);
            for(int i=0;i<freq;i++){
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}