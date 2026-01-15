package Graphs;
import java.util.*;
class Solution {
    public int[] shortestPath(int V, int[][] edges, int src) {
        // code here
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge:edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> q=new LinkedList<>();
        int[] dist=new int[V];
        
        Arrays.fill(dist,(int)1e9);
        q.offer(src);
        dist[src]=0;
        while(!q.isEmpty()){
            int node=q.poll();
            
            if(dist[node]!=(int)1e9){
                for(int neighbor:adj.get(node)){
                    if(dist[node]+1<dist[neighbor]){
                        dist[neighbor]=1+dist[node];
                        q.offer(neighbor);
                    }
                }
            }
        }
        
        for(int i=0;i<V;i++){
            if(dist[i]==(int)1e9){
                dist[i]=-1;
            }
        }
        return dist;
    }
}