package Graphs;
import java.util.*;
class Pair {
    int distance, node;
    Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

class dijkstraWithPQ {
    public int[] dijkstra(int V, int[][] edges, int src) {

        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e9);

    
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build graph (undirected)
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj.get(u).add(new Pair(w, v));
            adj.get(v).add(new Pair(w, u));
        }

        PriorityQueue<Pair> pq =
            new PriorityQueue<>((a, b) -> a.distance - b.distance);

        dist[src] = 0;
        pq.offer(new Pair(0, src));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int dis = curr.distance;
            int node = curr.node;

            if (dis > dist[node]) continue;

            for (Pair p : adj.get(node)) {
                int adjNode = p.node;
                int wt = p.distance;

                if (dis + wt < dist[adjNode]) {
                    dist[adjNode] = dis + wt;
                    pq.offer(new Pair(dist[adjNode], adjNode));
                }
            }
        }

        return dist;
    }
}