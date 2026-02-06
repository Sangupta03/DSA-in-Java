package Graphs;
import java.util.*;

class numberOfIslandTwo {
    public List<Integer> numOfIslands(int n, int m, int[][] A) {

        List<Integer> ans = new ArrayList<>();
        int[][] vis = new int[n][m];
        DisjointSet ds = new DisjointSet(n * m);

        int cnt = 0;

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        for (int i = 0; i < A.length; i++) {

            int row = A[i][0];
            int col = A[i][1];

            // already land
            if (vis[row][col] == 1) {
                ans.add(cnt);
                continue;
            }

            vis[row][col] = 1;
            cnt++;

            int nodeNo = row * m + col;

            // check 4 neighbors
            for (int d = 0; d < 4; d++) {
                int nr = row + drow[d];
                int nc = col + dcol[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m
                        && vis[nr][nc] == 1) {

                    int adjNode = nr * m + nc;

                    if (ds.findParent(nodeNo) != ds.findParent(adjNode)) {
                        cnt--;
                        ds.unionBySize(nodeNo, adjNode);
                    }
                }
            }

            ans.add(cnt);
        }

        return ans;
    }
}
class DisjointSet {
    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i < n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findParent(int node) {
        if (node == parent.get(node)) return node;
        int root = findParent(parent.get(node));
        parent.set(node, root);
        return root;
    }

    public void unionBySize(int u, int v) {
        int pu = findParent(u);
        int pv = findParent(v);
        if (pu == pv) return;

        if (size.get(pu) < size.get(pv)) {
            parent.set(pu, pv);
            size.set(pv, size.get(pv) + size.get(pu));
        } else {
            parent.set(pv, pu);
            size.set(pu, size.get(pu) + size.get(pv));
        }
    }
}
