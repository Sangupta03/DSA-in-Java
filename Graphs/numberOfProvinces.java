package Graphs;
import java.util.*;
class numberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        
        int n=isConnected.length;
        DisjointSet ds=new DisjointSet(n);

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(isConnected[i][j]==1 && i!=j){
                    ds.unionByRank(i,j);
                }
            }
        }
        int cnt=0;
        for(int i=0;i<n;i++){
            if(ds.findParent(i)==i){
                cnt++;
            }
        }
        return cnt;    
    }
}

class DisjointSet{
    public ArrayList<Integer> rank=new ArrayList<>();
    public ArrayList<Integer> parent=new ArrayList<>();

    public DisjointSet(int n){
        for(int i=0;i<=n;i++){
            rank.add(0);
            parent.add(i);
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
            parent.set(ulp_u,ulp_v);
            rank.set(ulp_v,rank.get(ulp_v)+1);
        }
    }
}