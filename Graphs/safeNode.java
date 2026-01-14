package Graphs;
import java.util.*;

class safeNode {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int V=graph.length;
        List<Integer> safeNodes=new ArrayList<>();
        int[] check=new int[V];
        int[] vis=new int[V];
        int[] pathvis=new int[V];

        for(int i=0;i<V;i++){
            if(vis[i]==0){
                dfs(i,vis,pathvis,check,graph);
            }
        }

        for(int i=0;i<V;i++){
            if(check[i]==1){
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }

    //cycle check
    //true: cycle found
    //false: no cycle found
    public boolean dfs(int node,int[] vis,int[] pathvis,int[] check,int[][] graph){
        vis[node]=1;
        pathvis[node]=1;

        check[node]=0;
        for(int neighbour:graph[node]){
            if(vis[neighbour]==0){
                if(dfs(neighbour,vis,pathvis,check,graph)==true){
                    check[node]=0;
                    return true;
                }
            }else if(pathvis[neighbour]==1){
                return true;
            }
        }
        check[node]=1;
        pathvis[node]=0;
        return false;
    }
}