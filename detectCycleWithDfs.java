import java.util.*;

class Solution {

    public boolean isCycle(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Build adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] vis = new boolean[V];

        // Check each connected component
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (detectCycleDfs(i, -1, vis, adj)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean detectCycleDfs(int node, int parentNode, boolean[] vis,
                                   ArrayList<ArrayList<Integer>> adj) {

        vis[node] = true;

        for (int neighbour : adj.get(node)) {

            if (!vis[neighbour]) {
                if (detectCycleDfs(neighbour, node, vis, adj)) {
                    return true;
                }
            } 
            else if (neighbour != parentNode) {
                return true; 
            }
        }

        return false;
    }
}

public class detectCycleWithDfs {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input number of vertices
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        // Input number of edges
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        int[][] edges = new int[E][2];

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < E; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }

        Solution obj = new Solution();
        boolean hasCycle = obj.isCycle(V, edges);

        if (hasCycle) {
            System.out.println("Cycle detected");
        } else {
            System.out.println("No cycle");
        }

        sc.close();
    }
}
