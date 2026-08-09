package Graphs;

class longestCycle {
    public int longestCycleQ(int[] edges) {
        int V=edges.length;

        int maxCycle=-1;
        boolean[] vis=new boolean[V];
        boolean[] pathvis=new boolean[V];
        int[] depth=new int[V];
        for(int i=0;i<V;i++){
            if(!vis[i]){
                int cycle=dfs(i,vis,pathvis,depth,edges);
                maxCycle=Math.max(maxCycle,cycle);
            }
        }
        return maxCycle;
    }

    public int dfs(int node,boolean[] vis,boolean[] pathvis,int[] depth,int[] edges){
        pathvis[node]=true;
        vis[node]=true;

        int nbh=edges[node];
        if(nbh!=-1){
            if(!vis[nbh]){
                depth[nbh]=1+depth[node];
                int cycle=dfs(nbh,vis,pathvis,depth,edges);
                if(cycle!=-1){
                    pathvis[node]=false; //so it can be used for other cycles also
                    return cycle;
                }
            }else if(pathvis[nbh]){
                pathvis[node]=false;
                //cycle found return depth
                return depth[node]-depth[nbh]+1;
            }
        }
        pathvis[node]=false;
        return -1;
    }
    //case of pathvis be careful, unmark always, set to false
}