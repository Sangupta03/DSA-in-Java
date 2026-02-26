package Heaps;

import java.util.*;
class KthLargestStream {
    PriorityQueue<Integer> minHeap;
    int k;
    public KthLargestStream(int k, int[] nums) {
        this.k=k;
        minHeap=new PriorityQueue<>();

        for(int num:nums){
            add(num);
        }
    }
    
    public int add(int val) {
        
        minHeap.offer(val);
        while(minHeap.size()>k){
            minHeap.poll();
        }
        return minHeap.peek();
    }
}