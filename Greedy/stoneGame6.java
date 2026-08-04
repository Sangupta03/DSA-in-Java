package Greedy;
import java.util.*;

class stoneGame6 {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->((b[0]+b[1])-(a[0]+a[1])));

        for(int i=0;i<aliceValues.length;i++){
            pq.offer(new int[]{aliceValues[i],bobValues[i]});
        }
        int alice=0;
        int bob=0;
        while(!pq.isEmpty()){
            int[] p1=pq.poll();
            alice+=p1[0];
            if(!pq.isEmpty()){
                int[] p2=pq.poll();
                bob+=p2[1];
            }
        }

        if(alice>bob) return 1;
        else if(alice<bob) return -1;
        else return 0;
    }
}
