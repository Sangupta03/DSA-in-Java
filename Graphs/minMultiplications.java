package Graphs;
import java.util.*;
class Pair{
    int first,second;
    Pair(int first,int second){
        this.first=first;//cost
        this.second=second;//value by multiplication
    }
}
class minMultiplications {
    int minimumMultiplications(int[] arr, int start, int end) {

        // Your code here
        Queue<Pair> q=new LinkedList<>();
        if(start==end) return 0;
        
        int[] dist=new int[100000];
        Arrays.fill(dist,(int)1e9);
        
        dist[start]=0;
        int MOD=(int)100000;
        q.offer(new Pair(0,start));
        
        while(!q.isEmpty()){
            Pair curr=q.poll();
            int step=curr.first;
            int value=curr.second;
         
            for(int it:arr){
                int distance=(value*it)%MOD;
                if(step+1<dist[distance]){
                    dist[distance]=step+1;
                    if(distance==end) return step+1;
                    q.offer(new Pair(step+1,distance));
                }
            }
        }
        return -1;
    }
}