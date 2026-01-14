package Graphs;
import java.util.*;
class CycleDetectTopoSort {
    public boolean isCyclic(int V, int[][] edges) {
        // code here
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge:edges){
            adj.get(edge[0]).add(edge[1]);
        }
        int[] indegree=new int[V];
        
        for(int i=0;i<V;i++){
            for(int neighbor:adj.get(i)){
                indegree[neighbor]++;
            }
        }
        Queue<Integer> q=new LinkedList<>();
        
        for(int i=0;i<V;i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }
        int cnt=0;
        while(!q.isEmpty()){
            int node=q.poll();
            cnt++;
            for(int neighbor :adj.get(node)){
                indegree[neighbor]--;
                if(indegree[neighbor]==0) q.offer(neighbor);
            }
        }
        if(cnt==V){ //no cycle as topo sort exists
            return false;
        }
        else return true;
    }
    
}