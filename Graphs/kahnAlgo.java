package Graphs;
import java.util.*;

class kahnAlgo {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge:edges){
            adj.get(edge[0]).add(edge[1]);
        }
        
        boolean[] vis=new boolean[V];
        Stack<Integer> stk=new Stack<>();
        
        for(int i=0;i<V;i++){
            if(!vis[i]){
                dfs(i,vis,adj,stk);
            }
        }
        
        ArrayList<Integer> ans=new ArrayList<>();
        while(!stk.isEmpty()){
            ans.add(stk.pop());
        }
        return ans;
    }
    
    public void dfs(int node,boolean[] vis,ArrayList<ArrayList<Integer>> adj,Stack<Integer> stk){
        
        vis[node]=true;
        
        for(int neighbour:adj.get(node)){
            if(!vis[neighbour]){
                dfs(neighbour,vis,adj,stk);
            }
        }
        stk.push(node);
    }
}