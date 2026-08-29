package Graphs;
import java.util.*;
//using floyd warshall

class negativeCycleFloydWarshall {
    public boolean isNegativeWeightCycle(int V, int[][] edges) {
        // code here
        int[][] dist=new int[V][V];
        
        for(int[] x:dist){
            Arrays.fill(x,(int)1e9);
        }
        
        for(int i=0;i<V;i++) dist[i][i]=0;
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            // If there are multiple edges between u and v, keep the minimum weight
            dist[u][v] = Math.min(dist[u][v], w);
        }
        
        for(int via=0;via<V;via++){
            for(int i=0;i<V;i++){
                for(int j=0;j<V;j++){
                    if(dist[i][via]!=(int) 1e9 && dist[via][j]!=(int)1e9){
                        dist[i][j]=Math.min(dist[i][j],dist[i][via]+dist[via][j]);
                    }
                }
            }
        }
        
        //node reaches itself with negative weight so cycle exists
        
        for(int i=0;i<V;i++){
            if(dist[i][i]<0){
                return true;
            }
        }
        return false;
    }
}
