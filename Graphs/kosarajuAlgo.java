package Graphs;
import java.util.*;
class kosarajuAlgo {
    // Function to find number of strongly connected components in the graph.
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
     
        int V=adj.size();
        boolean[] vis=new boolean[V];
        Stack<Integer> stk=new Stack<>();
        
        for(int i=0;i<V;i++){
            if(!vis[i]){
                dfs(i,adj,vis,stk);
            }
        }
        
        ArrayList<ArrayList<Integer>> adjT=new ArrayList<>();
        
        for(int i=0;i<V;i++) adjT.add(new ArrayList<>());
        
        for(int i=0;i<V;i++){
            vis[i]=false;
            for(Integer it:adj.get(i)){
                //i-->it
                //it-->i
                adjT.get(it).add(i);
            }
        }
        
        int scc=0;
        while(!stk.isEmpty()){
            int node=stk.pop();
            if(!vis[node]){
                scc++;
                dfs2(node,adjT,vis);
            }
        }
        return scc;
        
    }
    
    public void dfs2(int node,ArrayList<ArrayList<Integer>> adj,boolean[] vis){
        vis[node]=true;
        
        for(Integer it:adj.get(node)){
            if(!vis[it]){
                dfs2(it,adj,vis);
            }
        }
    }
    
    public void dfs(int node,ArrayList<ArrayList<Integer>> adj,boolean[] vis,Stack<Integer> stk){
        vis[node]=true;
        
        for(Integer it:adj.get(node)){
            if(!vis[it]){
                dfs(it,adj,vis,stk);
            }
        }
        stk.add(node);
    }
}