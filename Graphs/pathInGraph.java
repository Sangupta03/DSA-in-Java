package Graphs;
import java.util.*;
class pathInGraph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        DisjointSet ds=new DisjointSet(n);

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            if(ds.findParent(u)!=ds.findParent(v)){
                ds.unionBySize(u,v);
            }
        }

        if(ds.findParent(source)!=ds.findParent(destination)){
            return false;
        }
        return true;
    }
}
class DisjointSet{
    ArrayList<Integer> size=new ArrayList<>();
    ArrayList<Integer> parent=new ArrayList<>();

    DisjointSet(int n){
        for(int i=0;i<n;i++){
            parent.add(i);
            size.add(1);
        }
    }

    int findParent(int node){
        if(node==parent.get(node)) return node;
        int ulp=findParent(parent.get(node));
        parent.set(node,ulp);
        return parent.get(node);
    }

    void unionBySize(int u,int v){
        int ulp_u=findParent(u);
        int ulp_v=findParent(v);

        if(ulp_u==ulp_v) return;

        if(size.get(u)<size.get(v)){
            parent.set(ulp_u,ulp_v);
            size.set(ulp_v,size.get(u)+size.get(v));
        }else{
            parent.set(ulp_v,ulp_u);
            size.set(ulp_u,size.get(u)+size.get(v));
        }
    }
}