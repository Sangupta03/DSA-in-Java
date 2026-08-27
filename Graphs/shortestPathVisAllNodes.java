package Graphs;
import java.util.*;

class shortestPathVisAllNodes {
    public int shortestPathLength(int[][] graph) {
        int n=graph.length;
        int[][] dist=new int[n][1<<n]; //(1<<n) means in decimal combination 2^n

        for(int[] arr:dist){
            Arrays.fill(arr,(int) 1e9);
        }

        Queue<int[]> q=new LinkedList<>();

        //use concept of bitmasking since n<=12;
        //if node 1 vis then 0001, for 2 0010,etc
        int allVisited=(1<<n)-1;  //all visited mean 10000-1=01111

        //u can start from each and every node
        for(int i=0;i<n;i++){
            int mask=1<<i; //shift by i units to left eg 1<<2=0100
            dist[i][mask]=0;
            q.offer(new int[]{i,mask});
        }

        while(!q.isEmpty()){
            int[] curr=q.poll();
            int mask=curr[1];
            int node=curr[0];
            int distance = dist[node][mask];

            if(mask==allVisited){
                return dist[node][mask];
            }
            for(int nbh:graph[node]){
                int newMask=mask | (1<<nbh);  //oldmask+newmask 
                // OR stands for +, does not alter or turn of marked bits
                if(dist[nbh][newMask]==(int)1e9){
                    dist[nbh][newMask]=1+distance;
                    q.offer(new int[]{nbh,newMask});
                }
            }
        }
        return -1;
    }
}
