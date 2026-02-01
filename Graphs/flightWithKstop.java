package Graphs;
import java.util.*;
class Pair{
    int node;
    int distance;
    Pair(int node,int distance){
        this.node=node;
        this.distance=distance;
    }
}
class Tuple{
    //stop,node,cost
    int first,second,third;
    Tuple(int first,int second,int third){
        this.first=first;
        this.second=second;
        this.third=third;
    }
}
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Pair>> adj=new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] flight:flights){
            adj.get(flight[0]).add(new Pair(flight[1],flight[2]));
            //directed edge
        }

        Queue<Tuple> q=new LinkedList<>();
        int[] dist=new int[n];
        Arrays.fill(dist,(int) 1e9);
        dist[src]=0;
        q.offer(new Tuple(0,src,0));

        while(!q.isEmpty()){
            Tuple curr=q.poll();
            int stop=curr.first;
            int node=curr.second;
            int cost=curr.third;
            
            if(stop>k) continue;

            for(Pair it:adj.get(node)){
                int adjNode=it.node;
                int eCost=it.distance;

                if(cost+eCost<dist[adjNode] && stop<=k){
                    dist[adjNode]=cost+eCost;
                    q.offer(new Tuple(stop+1,adjNode,dist[adjNode]));
                }
            }
        }
        if(dist[dst]==(int)1e9) return -1;
        else return dist[dst];
    }
}
