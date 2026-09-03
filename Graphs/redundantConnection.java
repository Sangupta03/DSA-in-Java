package Graphs;
import java.util.*;
class redundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int n=edges.length;
        DisjointSet ds=new DisjointSet(n+1);

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            if(ds.findParent(u)!=ds.findParent(v)){
                ds.unionBySize(u,v);
            }else{
                int[] temp=new int[]{u,v};
                return temp;
            }
        }
        return new int[]{-1,-1};
    }
}

class DisjointSet{
    ArrayList<Integer> size=new ArrayList<>();
    ArrayList<Integer> parent=new ArrayList<>();

    DisjointSet(int n){
        for(int i=0;i<n;i++){
            size.add(0);
            parent.add(i);
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

        if(size.get(ulp_u)<size.get(ulp_v)){
            parent.set(ulp_u,ulp_v);
            size.set(ulp_v,size.get(ulp_v)+size.get(ulp_u));
        }else{
            parent.set(ulp_v,ulp_u);
            size.set(ulp_u,size.get(ulp_u)+size.get(ulp_v));
        }
    }
}