package Greedy;
import java.util.*;

class optimalFileMerge {
    public int minComputation(int[] files) {
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        
        for(int x:files){
            pq.offer(x);
        }
        
        int total=0;
        while(pq.size()>1){
            int ele1=pq.poll();
            int ele2=pq.poll();
            int sum=ele1+ele2;
            total+=sum;
            pq.offer(sum);
        }
        
        return total;
    }
}
