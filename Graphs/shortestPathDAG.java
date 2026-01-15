package Graphs;
import java.util.*;

// Pair class to store (destination, weight)
class Pair {
    int first;   
    int second;  

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {

    public int[] shortestPath(int V, int E, int[][] edges) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // add directed edges
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
        }

        //Topological sort using DFS
        Stack<Integer> stk = new Stack<>();
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                topoDfs(i, vis, adj, stk);
            }
        }

        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e9);
        dist[0] = 0;   // source node = 0

        // Relax edges in topo order 
        while (!stk.isEmpty()) {
            int node = stk.pop();

            // relax only if reachable
            if (dist[node] != (int)1e9) {
                for (Pair neighbor : adj.get(node)) {
                    int v = neighbor.first;
                    int wt = neighbor.second;

                    if (dist[node] + wt < dist[v]) {
                        dist[v] = dist[node] + wt;
                    }
                }
            }
        }

        // Convert unreachable nodes to -1 
        for (int i = 0; i < V; i++) {
            if (dist[i] == (int)1e9) {
                dist[i] = -1;
            }
        }

        return dist;
    }

    // DFS for topological sorting
    public void topoDfs(int node, boolean[] vis,
                        ArrayList<ArrayList<Pair>> adj, Stack<Integer> stk) {

        vis[node] = true;

        for (Pair neighbor : adj.get(node)) {
            int v = neighbor.first;
            if (!vis[v]) {
                topoDfs(v, vis, adj, stk);
            }
        }

        // push after visiting all neighbors
        stk.push(node);
    }
}
