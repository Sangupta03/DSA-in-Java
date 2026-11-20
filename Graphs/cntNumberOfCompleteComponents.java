package Graphs;
import java.util.*;

class cntNumberOfCompleteComponents {
    public int countCompleteComponents(int n, int[][] edges) {
        
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited=new boolean[n];
        int cnt=0;

        //find the number of complete components here
        // we need to know the degree of each vertice in a component and check if it is equal to the no. of interconnected edges that must exist in the complete component 
        for(int i=0;i<n;i++){
            if(!visited[i]){
                ArrayList<Integer> component=new ArrayList<>();
                dfs(i,visited,adj,component);
                
                boolean complete=true;
                int size=component.size();

                for(int node:component){
                    if(adj.get(node).size()!=size-1){ //degree==no. of vertices-1 or edges connected
                        complete=false;
                    }
                }
                if(complete){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public void dfs(int node,boolean[] visited,ArrayList<ArrayList<Integer>> adj,ArrayList<Integer> component){

        visited[node]=true;
        component.add(node);

        for(int nbh:adj.get(node)){
            if(!visited[nbh]){
                dfs(nbh,visited,adj,component);
            }
        }
    }
}