package Graphs;
import java.util.*;

class Pair{
    int first; //time
    int second; //node
    Pair(int first,int second){
        this.first=first;
        this.second=second;
    }
}
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Pair>> adj=new ArrayList<>();

        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());

        for(int[] time:times){
            adj.get(time[0]).add(new Pair(time[2],time[1]));
        }
        int[] dist=new int[n+1];
        Arrays.fill(dist,(int) 1e9);
        dist[k]=0;
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->a.first-b.first);//sort by time

        pq.offer(new Pair(0,k));
        while(!pq.isEmpty()){
            Pair curr=pq.poll();
            int distance=curr.first;
            int node=curr.second;

            if(distance>dist[node]) continue;

            for(Pair it:adj.get(node)){
                int adjNode=it.second;
                int time=it.first;
                if(time+distance<dist[adjNode]){
                    dist[adjNode]=time+distance;
                    pq.offer(new Pair(dist[adjNode],adjNode));
                }
            }
        }
        int ans=0;
        for(int i=1;i<=n;i++){
            ans=Math.max(ans,dist[i]);
        }
        if(ans==(int)1e9) return -1;
        else return ans;
    }
}