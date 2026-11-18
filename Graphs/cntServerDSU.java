package Graphs;
import java.util.*;
class cntServerDSU {
    public int countServers(int[][] grid) {
        
        int n=grid.length;
        int m=grid[0].length;

        DisjointSetUnion ds=new DisjointSetUnion(n*m); 
        //total n*m nodes;

        int[] rows=new int[n];
        int[] cols=new int[m];
        Arrays.fill(rows,-1);
        Arrays.fill(cols,-1);

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){

                if(grid[i][j]==1){
                    int node=i*m+j;

                    //same row case
                    if(rows[i]==-1){
                        rows[i]=node;
                    }else{
                        ds.unionByRank(rows[i],node);
                    }

                    //same col case
                    if(cols[j]==-1){
                        cols[j]=node;
                    }else{
                        //connect nodes in same col
                        ds.unionByRank(cols[j],node);
                    }
                }
            }
        }

        int[] count=new int[n*m];
        //helps count no. of nodes in each shared component

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    int node=i*m+j;

                    int root=ds.findParent(node);
                    count[root]++;
                }
            }
        }
        int ans=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    int node=i*m+j;
                    int root=ds.findParent(node);

                    if(count[root]>1){
                        ans++;
                    }
                }
            }
        }
        
        return ans;

    }
}
class DisjointSetUnion{

    ArrayList<Integer> parent=new ArrayList<>();
    ArrayList<Integer> rank=new ArrayList<>();

    DisjointSetUnion(int n){
        for(int i=0;i<n;i++){
            parent.add(i);
        
            rank.add(1);
        }
    }

    public int findParent(int node){
        if(node==parent.get(node)){
            return node;
        }
        int ulp=findParent(parent.get(node));
        parent.set(node,ulp);
        return parent.get(node);
    }

    public void unionByRank(int a,int b){
        int ulp_a=findParent(a);
        int ulp_b=findParent(b);

        if(ulp_a==ulp_b){
            return;
        }
        if(rank.get(ulp_a)>rank.get(ulp_b)){
            parent.set(ulp_b,ulp_a);
        }else if(rank.get(ulp_b)>rank.get(ulp_a)){
            parent.set(ulp_a,ulp_b);
        }else{
            parent.set(ulp_a,ulp_b);
            rank.set(ulp_b,rank.get(ulp_b)+1);
        }
    }
}

