package Graphs;
import java.util.*;

class flightWithKStopsBellmanFord {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist=new int[n];
        Arrays.fill(dist,(int)1e9);
        ArrayList<ArrayList<int[]>> adj=new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] flight:flights){
            adj.get(flight[0]).add(new int[]{flight[1],flight[2]});
            //directed edge
        }

        dist[src]=0;
        Queue<Pair> q=new LinkedList<>();
        q.offer(new Pair(0,src,0));

        while(!q.isEmpty()){
            Pair curr=q.poll();
            int distance=curr.dist;
            int node=curr.node;
            int stops=curr.stops;

            if(stops>k) continue;
            if(distance>dist[node]) continue;

            for(int[] p:adj.get(node)){
                int nNode=p[0];
                int nDist=p[1];

                if(nDist+distance<dist[nNode]){
                    dist[nNode]=nDist+distance;
                    q.offer(new Pair(dist[nNode],nNode,stops+1));
                }
            }
        }
        if(dist[dst]==(int)1e9){
            return -1;
        }
        return dist[dst];
    }
}

class Pair{
    int dist;
    int node;
    int stops;
    Pair(int dist,int node,int stops){
        this.dist=dist;
        this.node=node;
        this.stops=stops;
    }
}

class Solution {
    public static int findCheapestCost(int n, int[][] edges, int src, int dst, int k) {
        // code here
        ArrayList<ArrayList<int[]>> adj=new ArrayList<>();
        
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        int[] dist=new int[n];
        
        Arrays.fill(dist,(int)1e9);
        dist[src]=0;
        
        for(int i=0;i<=k;i++){ //k+1
            int[] temp=dist.clone();
            for(int[] edge:edges){
                int u=edge[0];
                int v=edge[1];
                int w=edge[2];
                if(dist[u]!=(int)1e9 && dist[u]+w<temp[v]){
                    temp[v]=w+dist[u];
                }
            }
            dist=temp;
        }
        return dist[dst]==(int)1e9?-1:dist[dst];
    }
}