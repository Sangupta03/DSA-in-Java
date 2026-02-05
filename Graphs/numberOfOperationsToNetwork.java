package Graphs;
import java.util.*;
class numberOfOperationsToNetwork {
    public int makeConnected(int n, int[][] connections) {
        int extra=0;
        if(connections.length < n-1) return -1; //n-1 cables needed for n nodes;

        DisjointSet ds=new DisjointSet(n); 
        int x=connections.length;
        
        for(int i=0;i<x;i++){
            int u=connections[i][0];
            int v=connections[i][1];

            if(ds.findParent(u)==ds.findParent(v)){
                extra++;
            }else{
                ds.unionByRank(u,v);
            }
        }

        int cnt=0;
        for(int i=0;i<n;i++){
            if(ds.findParent(i)==i){
                cnt++;
            }
        }

        if(extra>=(cnt-1)){
            return cnt-1;
        }else{
            return -1;
        }
    }
}
class DisjointSet{
    public ArrayList<Integer> parent=new ArrayList<>();
    public ArrayList<Integer> rank=new ArrayList<>();

    public DisjointSet(int n){
        for(int i=0;i<=n;i++){
            parent.add(i);
            rank.add(0);
        }
    }

    public int findParent(int node){
        if(node==parent.get(node)) return node;
        int ulp=findParent(parent.get(node));
        parent.set(node,ulp);
        return ulp;
    }

    public void unionByRank(int u,int v){
        int ulp_u=findParent(u);
        int ulp_v=findParent(v);

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