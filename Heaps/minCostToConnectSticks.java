package Heaps;
import java.util.*;
class minCostToConnectSticks {
    public static int minCost(int[] arr) {
        // code here
        if (arr.length<=1) return 0;
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();
        
        for(int rope:arr){
            minHeap.offer(rope);
        }
        
        int totalCost=0;
        
        while(minHeap.size()>1){
            int len1=minHeap.poll();
            int len2=minHeap.poll();
            
            int sum=len1+len2;
            totalCost+=sum;
            minHeap.offer(sum);
        }
        return totalCost;
    }
}