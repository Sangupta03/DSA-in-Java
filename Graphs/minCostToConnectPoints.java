package Graphs;
import java.util.*;
class minCostToConnectPoints {
    public int minCostConnectPoints(int[][] points) {
        int n=points.length;
        boolean[] visited=new boolean[n];
        int[] minDist=new int[n];
        Arrays.fill(minDist,Integer.MAX_VALUE);
        minDist[0]=0;

        int cost=0;

        for(int i=0;i<n;i++){
            int u=-1;  //find closest edge each time

            // Find the unvisited point with minimum cost
            for(int j=0;j<n;j++){
                if(!visited[j] && (u==-1 || minDist[j]<minDist[u])){
                    u=j;
                }
            }

            cost+=minDist[u];
            visited[u]=true;

            //calculate minDist from point u to all other points
            for(int v=0;v<n;v++){

                if(!visited[v]){
                    int dist=Math.abs(points[v][1]-points[u][1])+Math.abs(points[v][0]-points[u][0]);

                    minDist[v]=Math.min(minDist[v],dist);
                }
            }
        }
        return cost;
       
    }
}
