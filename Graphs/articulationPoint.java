package Graphs;
import java.util.*;

class articulationPoint {

    public int timer = 0;

    public ArrayList<Integer> articulationPoints(int V,
                                                 ArrayList<ArrayList<Integer>> adj) {

        timer = 0;

        int[] tim = new int[V];
        int[] low = new int[V];
        int[] vis = new int[V];
        int[] mark = new int[V];

        for(int i=0;i<V;i++){
            if(vis[i]==0){
                dfs(i,-1,adj,tim,low,vis,mark);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        // collect marked articulation points
        for(int i=0;i<V;i++){
            if(mark[i]==1){
                ans.add(i);
            }
        }

        // check AFTER collecting
        if(ans.size()==0)
            return new ArrayList<>(Arrays.asList(-1));

        Collections.sort(ans);
        return ans;
    }


    public void dfs(int node,int parent,
                    ArrayList<ArrayList<Integer>> adj,
                    int[] tim,int[] low,int[] vis,int[] mark){

        vis[node] = 1;
        tim[node] = low[node] = timer++;

        int child = 0;

        for(int it: adj.get(node)){

            if(it == parent) continue;

            if(vis[it] == 0){

                dfs(it,node,adj,tim,low,vis,mark);

                // tree-edge update
                low[node] = Math.min(low[node], low[it]);

                // articulation condition (non-root)
                if(parent != -1 && low[it] >= tim[node]){
                    mark[node] = 1;
                }

                child++;

            } else {
                //back-edge update
                low[node] = Math.min(low[node], tim[it]);
            }
        }

        // root articulation rule
        if(parent == -1 && child > 1){
            mark[node] = 1;
        }
    }
}

