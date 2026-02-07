package Graphs;
import java.util.*;
class makingLargeIsland {
    public int largestIsland(int[][] grid) {

        int n=grid.length;
        DisjointSet ds=new DisjointSet(n*n);

        int[] drow={-1,0,1,0};
        int[] dcol={0,1,0,-1};
        //create islands
        for(int row=0;row<n;row++){
            for(int col=0;col<n;col++){
                int node=row*n+col;

                if(grid[row][col]==0) continue;

                for(int d=0;d<4;d++){
                    int nrow=drow[d]+row;
                    int ncol=dcol[d]+col;
                    if(nrow<n && ncol<n && nrow>=0 && ncol>=0 && grid[nrow][ncol]==1){
                        int adjNode=nrow*n+ncol;
                        ds.unionBySize(adjNode,node);
                    }
                }
            }
        }

        int mx=0;
        //find zeroes and check
        for(int row=0;row<n;row++){
            for(int col=0;col<n;col++){
                int node=row*n+col;

                if(grid[row][col]==1) continue;

                int sizeTotal=0;
                HashSet<Integer> st=new HashSet<>();
                for(int d=0;d<4;d++){
                    int nrow=drow[d]+row;
                    int ncol=dcol[d]+col;

                    if(nrow>=0 && ncol>=0 && nrow<n && ncol<n && grid[nrow][ncol]==1){
                        int adjNode=nrow*n+ncol;
                        st.add(ds.findParent(adjNode));
                    }
                }
                for(int it:st){
                    sizeTotal+=ds.size.get(it);
                }
                mx=Math.max(mx,sizeTotal+1);
            }
        }
        //if whole matrix is 1;
        for(int nodeId=0;nodeId<n*n;nodeId++){
            mx=Math.max(mx,ds.size.get(ds.findParent(nodeId)));
        }
        return mx;
    }
}

class DisjointSet{
    ArrayList<Integer> size=new ArrayList<>();
    ArrayList<Integer> parent=new ArrayList<>();

    DisjointSet(int n){
        for(int i=0;i<=n;i++){
            size.add(1);
            parent.add(i);
        }
    }

    public int findParent(int node){
        if(node==parent.get(node)) return node;
        int ulp=findParent(parent.get(node));
        parent.set(node,ulp);
        return ulp;
    }
    public void unionBySize(int u,int v){
        int ulp_u=findParent(u);
        int ulp_v=findParent(v);

        if (ulp_u == ulp_v) return;
        if(size.get(ulp_u)<size.get(ulp_v)){
            parent.set(ulp_u,ulp_v);
            size.set(ulp_v,size.get(ulp_v)+size.get(ulp_u));
        }else{
            parent.set(ulp_v,ulp_u);
            size.set(ulp_u,size.get(ulp_u)+size.get(ulp_v));
        }
    }
}