package Heaps;
import java.util.*;
class MedianFinder {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    public MedianFinder() {
        minHeap=new PriorityQueue<>();
        maxHeap=new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if(minHeap.size()> maxHeap.size()) maxHeap.offer(minHeap.poll());
    }
    
    public double findMedian() {
        if(minHeap.size()==maxHeap.size()) return (maxHeap.peek()+minHeap.peek())/2.0;
        else return maxHeap.peek();
    }
}
