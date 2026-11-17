package Graphs;
import java.util.*;

//using dijkstra algo since shortest path with weighted graph
class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        ArrayList<ArrayList<Pair>> adj=new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];
            adj.get(u).add(new Pair(v,w));
        }

        int[][] dist=new int[n][k+1];
        //2D dist array since from u->v , even though minDist is same, the cnt of identical characters may be different and less than k, so many valid paths might reach the destination, and one of these paths can result in the minPath

        for(int[] x:dist){
            Arrays.fill(x,Integer.MAX_VALUE);
        }

        dist[0][1]=0;  //src

        PriorityQueue<States> pq=new PriorityQueue<>((a,b)-> Integer.compare(a.wt,b.wt));
        pq.offer(new States(0,0,1));

        while(!pq.isEmpty()){
            States s=pq.poll();
            int d=s.wt;
            int node=s.node;
            int cnt=s.cnt;

            if(d>dist[node][cnt]) continue;
            if(node==n-1) return d;

            for(Pair p: adj.get(node)){
                int adjNode=p.node;
                int adjDist=p.wt;

                int newCnt=(labels.charAt(node)==labels.charAt(adjNode))?cnt+1:1;

                if(newCnt>k) continue;

                int newDist=adjDist+d;

                if(newDist<dist[adjNode][newCnt]){
                    dist[adjNode][newCnt]=newDist;
                    pq.offer(new States(adjNode,newDist,newCnt));
                }
            }
        }
        return -1;
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

class States{
    int node;
    int wt;
    int cnt;
    States(int node,int wt,int cnt){
        this.node=node;
        this.wt=wt;
        this.cnt=cnt;
    }
}
