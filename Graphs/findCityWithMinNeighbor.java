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