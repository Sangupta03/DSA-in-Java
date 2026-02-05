package Graphs;
import java.util.*;

class krusKalsAlgo {
    static int kruskalsMST(int V, int[][] edges) {
        // code here
        int edgeCnt=0;
        disjointSet ds=new disjointSet(V);
        
        //sort by weight
        Arrays.sort(edges,(a,b)->a[2]-b[2]);
        int mst_sum=0;
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int wt=edge[2];
            
            if(ds.findParent(u)!=ds.findParent(v)){
                mst_sum+=wt;
                ds.unionBySize(u,v);
                edgeCnt++;
                
                if(edgeCnt==V-1) break;
            }
        }
        return mst_sum;
    }
}

class disjointSet{
    ArrayList<Integer> parent=new ArrayList<>();
    ArrayList<Integer> size=new ArrayList<>();
    
    public disjointSet(int n){
        for(int i=0;i<=n;i++){
            parent.add(i);
            size.add(1);
        }
    }
    
    public int findParent(int node){
        if(node==parent.get(node)) return node;
        int ulp=findParent(parent.get(node));
        parent.set(node,ulp);
        return parent.get(node);
    }
    
    public void unionBySize(int u,int v){
        int ulp_u=findParent(u);
        int ulp_v=findParent(v);
        
        if(ulp_u==ulp_v) return;
        
        if(size.get(ulp_u)<size.get(ulp_v)){
            parent.set(ulp_u,ulp_v);
            size.set(ulp_v,size.get(ulp_v)+size.get(ulp_u));
        }else{
            parent.set(ulp_v,ulp_u);
            size.set(ulp_u,size.get(ulp_u)+size.get(ulp_v));
        }
    }
}