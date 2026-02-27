package Heaps;
import java.util.*;
class topKFrequentele {
    public int[] topKFrequent(int[] nums, int k) {
        
        HashMap<Integer,Integer> hp=new HashMap<>();

        for(int x:nums){
            hp.put(x,hp.getOrDefault(x,0)+1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> heap=new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        
        for(Map.Entry<Integer,Integer> entry:hp.entrySet()){
            heap.offer(entry);
            if(heap.size()>k){
                heap.poll();
            }
        }
        int[] res=new int[k];
        for(int i=k-1;i>=0;i--){
            res[i]=heap.poll().getKey();
        }
        return res;
    }
}