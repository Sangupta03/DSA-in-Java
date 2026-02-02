package Graphs;
class findCityWithMinNeighbor {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        
        int[][] dist=new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dist[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int wt=edge[2];
            dist[u][v]=wt;
            dist[v][u]=wt;
        }
        for(int i=0;i<n;i++) dist[i][i]=0;

        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][k]!=Integer.MAX_VALUE && dist[k][j]!=Integer.MAX_VALUE){
                        dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
                    }
                }
            }
        }

        int minCnt=n;
        int cityNo=-1;

        for(int city=0;city<n;city++){
            int cnt=0;
            for(int adj=0;adj<n;adj++){
                if(dist[city][adj]<=distanceThreshold){
                    cnt++;
                }
            }
            if(cnt<=minCnt){
                minCnt=cnt;
                cityNo=city;
            }
        }
        return cityNo;
    }
}

//Method 2

// class Pair{
//     int distance;
//     int node;
//     Pair(int distance,int node){
//         this.distance=distance;
//         this.node=node;
//     }
// }
// class Solution {
//     public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        
//         ArrayList<ArrayList<Pair>> adj=new ArrayList<>();
        
//         for(int i=0;i<n;i++) adj.add(new ArrayList<>());

//         for(int[] edge:edges){
//             adj.get(edge[0]).add(new Pair(edge[2],edge[1]));
//             adj.get(edge[1]).add(new Pair(edge[2],edge[0]));
//         }

//         int minReachable=n;
//         int cityNo=-1;
//         for (int i=0;i<n;i++){
//             int reachable=dijkstraAlgo(i,adj,n,distanceThreshold);
//             if(minReachable>=reachable && i>cityNo){
//                 minReachable=reachable;
//                 cityNo=i;
//             }
//         }
//         return cityNo;
//     }

//     public int dijkstraAlgo(int src,ArrayList<ArrayList<Pair>> adj,int n,int threshold){

//         int[] dist=new int[n];
//         Arrays.fill(dist,(int)1e9);

//         dist[src]=0;
//         PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->a.distance-b.distance);

//         pq.offer(new Pair(0,src));

//         while(!pq.isEmpty()){
//             Pair curr=pq.poll();
//             int node=curr.node;
//             int distance=curr.distance;

//             if(distance>dist[node]) continue;

//             for(Pair it:adj.get(node)){
//                 int adjNode=it.node;
//                 int wt=it.distance;

//                 if(distance+wt<dist[adjNode]){
//                     dist[adjNode]=wt+distance;
//                     pq.offer(new Pair(dist[adjNode],adjNode));
//                 }
//             }
//         }

//         int cnt=0;
//         for(int i=0;i<n;i++){
//             if(dist[i]!=(int) 1e9 && i!=src && dist[i]<=threshold){
//                 cnt++;
//             }
//         }
//         return cnt;
//     }
// }