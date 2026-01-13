package Graphs;
import java.util.*;

class CycleDetectDirectedGraph {
    public boolean isCyclic(int V, int[][] edges) {
        // code here
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge:edges){
            adj.get(edge[0]).add(edge[1]);
        }
        
        int[] vis=new int[V];
        
        
        for(int i=0;i<V;i++){
            if(vis[i]==0){
                if(dfs(i,vis,adj)==true){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean dfs(int node,int[] vis,ArrayList<ArrayList<Integer>> adj){
        
        vis[node]=2;
        
        for(int neighbour:adj.get(node)){
            if(vis[neighbour]==0){
                if(dfs(neighbour,vis,adj)==true){
                    return true;
                }
            }else if(vis[neighbour]==2){
                return true;
            }
        }
        //backtrack
        vis[node]=1;
        return false;
    }
}