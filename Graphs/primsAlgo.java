package Graphs;
import java.util.*;
class Pair{
    int distance,node;
    Pair(int node,int distance){
        this.node=node;
        this.distance=distance;
    }
    
}
class primsAlgo {
    public int spanningTree(int V, int[][] edges) {
        // code here
        int[] vis=new int[V];
        
        ArrayList<ArrayList<Pair>> adj=new ArrayList<>();
        for(int i=0;i<V;i++) adj.add(new ArrayList<>());
        
        for(int[] edge:edges){
            adj.get(edge[0]).add(new Pair(edge[1],edge[2]));
            adj.get(edge[1]).add(new Pair(edge[0],edge[2]));
        }
        
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->a.distance-b.distance);
        pq.offer(new Pair(0,0));// no parent node defined as no MST needed to track
        int sum=0;
        while(!pq.isEmpty()){
            Pair curr=pq.poll();
            int node=curr.node;
            int distance=curr.distance;
            
            if(vis[node]==1) continue;
            //add to MST
            vis[node]=1;
            sum+=distance;
            
            for(Pair it:adj.get(node)){
                int adjNode=it.node;
                int wt=it.distance;
                if(vis[adjNode]!=1){
                    pq.offer(new Pair(adjNode,wt));
                }
            }
        }
        return sum;
    }
}