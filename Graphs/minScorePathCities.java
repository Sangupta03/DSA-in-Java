package Graphs;
import java.util.*;
class minScorePathCities {
    public int minScore(int n, int[][] roads) {

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] road : roads){

            int u = road[0];
            int v = road[1];
            int w = road[2];

            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }

        boolean[] vis = new boolean[n + 1];

        return dfs(1, adj, vis, Integer.MAX_VALUE);
    }

    public int dfs(int node, ArrayList<ArrayList<int[]>> adj,
                   boolean[] vis, int ans){

        vis[node] = true;

        for(int[] nbh : adj.get(node)){

            int nNode = nbh[0];
            int wt = nbh[1];

            ans = Math.min(ans, wt);

            if(!vis[nNode]){
                ans = dfs(nNode, adj, vis, ans);
            }
        }

        return ans;
    }
}