package Graphs;
import java.util.*;

class minCostWalkInWeightedGraph {
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int ansLen=query.length;
        int[] ans=new int[ansLen];

        DisjointSet ds=new DisjointSet(n);

        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            
            if(ds.findParent(u)!=ds.findParent(v)){
                ds.unionByRank(u,v); 
            }
        }
        int[] componentCost=new int[n];
        Arrays.fill(componentCost,-1);

        for(int[] edge:edges){
            int u=edge[0];
            int wt=edge[2];
            int parent=ds.findParent(u);
            componentCost[parent]&=wt;
        }

        //& of all components in a unique connected component gives min cost for all nodes in that component
        
        int idx=0;
        for(int[] quer:query){
            int u=quer[0];
            int v=quer[1];

            if(ds.findParent(u)!=ds.findParent(v)){
                ans[idx++]=-1;
            }else{
                ans[idx++]=componentCost[ds.findParent(u)];
            }
        }
        return ans;
    }
}
class DisjointSet{
    ArrayList<Integer> rank=new ArrayList<>();
    ArrayList<Integer> parent=new ArrayList<>();

    DisjointSet(int n){
        for(int i=0;i<n;i++){
            parent.add(i);
            rank.add(0);
        }
    }

    int findParent(int node){
        if(node==parent.get(node)) return node;
        int ulp=findParent(parent.get(node));
        parent.set(node,ulp);
        return parent.get(node);
    }

    void unionByRank(int u,int v){
        int ulp_u=findParent(u);
        int ulp_v=findParent(v);

        if(ulp_v==ulp_u) return;

        if(rank.get(ulp_u)<rank.get(ulp_v)){
            parent.set(ulp_u,ulp_v);
        }else if(rank.get(ulp_v)<rank.get(ulp_u)){
            parent.set(ulp_v,ulp_u);
        }else{
            parent.set(ulp_v,ulp_u);
            rank.set(ulp_u,rank.get(ulp_u)+1);
        }
    }
}