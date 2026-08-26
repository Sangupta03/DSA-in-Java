package Graphs;
import java.util.*;
class dijkstraAlgo {
    public ArrayList<Integer> dijkstra(int V, int[][] edges, int src) {
        // code here
        int[] dist=new int[V];
        ArrayList<ArrayList<Pair>> adj=new ArrayList<>();
        
        for(int i=0;i<V;i++){
            ArrayList<Pair> temp=new ArrayList<>();
            adj.add(temp);
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];
            adj.get(u).add(new Pair(v,w));
            adj.get(v).add(new Pair(u,w));
        }
        
        Arrays.fill(dist,(int)1e9);
        dist[src]=0;
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->a.wt-b.wt);
        pq.offer(new Pair(src,0));
        
        while(!pq.isEmpty()){
            int distance=pq.peek().wt;
            int node=pq.peek().node;
            pq.poll();
            
            if(distance>dist[node]) continue;
            
            for(Pair nbh:adj.get(node)){
                int adjNode=nbh.node;
                int weight=nbh.wt;
                if(distance+weight<dist[adjNode]){
                    dist[adjNode]=distance+weight;
                    pq.offer(new Pair(adjNode,dist[adjNode]));
                }
            }
        }
        ArrayList<Integer> arr=new ArrayList<>();
        for(int x:dist){
            arr.add(x);
        }
        return arr;
    }
}
class Pair{
    int node;
    int wt;
    Pair(int node,int wt){
        this.node=node;
        this.wt=wt;
    }
}