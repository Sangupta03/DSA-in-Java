package Graphs;

import java.util.*;
class bellmanFord {
    public int[] bellmanFordAlgo(int V, int[][] edges, int src) {
        // code here
        int[] dist=new int[V];
        Arrays.fill(dist,(int)1e8);
        
        dist[src]=0;
        for(int i=0;i<V-1;i++){
            for(int[] edge:edges){
                int u=edge[0];
                int v=edge[1];
                int wt=edge[2];
                if(dist[u]!=(int)1e8 && dist[u]+wt<dist[v]){
                    dist[v]=dist[u]+wt;
                }
            }
        }
        
        //last nth relaxation for negative cycle
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int wt=edge[2];
            if(dist[u]!=(int)1e8 && dist[u]+wt<dist[v]){
                return new int[]{-1};
            }
        }
        return dist;
    }
}
