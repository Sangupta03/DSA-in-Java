class bipartiteGraphBfs {
    public boolean isBipartite(int V, int[][] edges) {
        // Code here
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge:edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        int[] color=new int[V];
        Arrays.fill(color,-1);
        
        for(int i=0;i<V;i++){
            if(color[i]==-1){
                if(checkColor(i,adj,color)==false){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkColor(int node,ArrayList<ArrayList<Integer>> adj,int[] color){
        
        Queue<Integer> q=new LinkedList<>();
        q.offer(node);
        color[node]=0;
        
        while(!q.isEmpty()){
            int x=q.poll();
            
            for(int neighbour:adj.get(x)){
                if(color[neighbour]==-1){
                    color[neighbour]=1-color[x];
                    q.offer(neighbour);
                }else if(color[neighbour]==color[x]){
                    return false;
                }
            }
        }
        return true;
    }
}
