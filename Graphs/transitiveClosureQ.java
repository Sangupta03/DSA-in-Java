package Graphs;

import java.util.*;

class transitiveClosureQ {
    public ArrayList<ArrayList<Integer>> transitiveClosure(int adj[][]) {
        int n = adj.length;
        int[][] reach = new int[n][n];
        
        //initialize the reachability matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // A node always reaches itself, OR if an edge exists in adj
                if (i == j || adj[i][j] == 1) {
                    reach[i][j] = 1;
                }
            }
        }
        
        // Floyd-Warshall boolean reachability loop
        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // i can reach j if it can already reach j, 
                    // OR if it can go from i -> via and via -> j
                    if (reach[i][j] == 1 || (reach[i][via] == 1 && reach[via][j] == 1)) {
                        reach[i][j] = 1;
                    }
                }
            }
        }
        
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                temp.add(reach[i][j]);
            }
            ans.add(temp);
        }
        
        return ans;
    }
}

