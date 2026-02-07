package Graphs;
import java.util.*;
class tarjanAlgo {

    // global timer to assign discovery times in DFS
    public int timer = 1;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        // build adjacency list (undirected graph)
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        for(List<Integer> edge : connections){
            int u = edge.get(0);
            int v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] vis = new int[n];   // visited flag
        int[] tim = new int[n];   // discovery time of node
        int[] low = new int[n];   // lowest reachable discovery time

        List<List<Integer>> ans = new ArrayList<>();

        // ⭐ run DFS for every component (important for disconnected graph)
        for(int i = 0; i < n; i++){
            if(vis[i] == 0){
                dfs(i, -1, adj, vis, low, tim, ans);
            }
        }

        return ans;
    }


    public void dfs(int node, int parent,
                    ArrayList<ArrayList<Integer>> adj,
                    int[] vis, int[] low, int[] tim,
                    List<List<Integer>> ans){

        vis[node] = 1;

        // ⭐ assign discovery time & initialize low-link value
        tim[node] = low[node] = timer;
        timer++;

        // explore neighbors
        for(int it : adj.get(node)){

            // skip the edge back to parent (not a back-edge)
            if(it == parent) continue;

            // ⭐ tree-edge (forward DFS)
            if(vis[it] == 0){

                dfs(it, node, adj, vis, low, tim, ans);

                // ⭐ after DFS, update low using child’s low value
                low[node] = Math.min(low[node], low[it]);

                // ⭐ BRIDGE CHECK:
                // if child cannot reach an ancestor of node,
                // removing this edge disconnects graph
                if(low[it] > tim[node]){
                    ans.add(Arrays.asList(node, it));
                }
            }
            else {
                // ⭐ back-edge found → update low using neighbor’s discovery time
        
                low[node] = Math.min(low[node], tim[it]);
            }
        }
    }
}
