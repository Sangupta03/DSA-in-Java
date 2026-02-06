package Graphs;
import java.util.*;
class mostStonesWithSameRowCol {
    public int removeStones(int[][] stones) {
        int n=stones.length;

        int rowMax=0;
        int colMax=0;
        for(int i=0;i<n;i++){
            rowMax=Math.max(rowMax,stones[i][0]);
            colMax=Math.max(colMax,stones[i][1]);
        }
        HashMap<Integer,Integer> hm=new HashMap<>();
        DisjointSet ds=new DisjointSet(rowMax+colMax+1);

        for(int i=0;i<n;i++){
            int row=stones[i][0];
            int col=stones[i][1]+rowMax+1;
            ds.unionBySize(row,col);
            hm.put(row,1);
            hm.put(col,1);
        }

        int cnt=0;
        for(HashMap.Entry<Integer,Integer> mp: hm.entrySet()){
            if(mp.getKey()==ds.findParent(mp.getKey())){
                cnt++;
            }
        }
        return n-cnt;
    }
}

class DisjointSet{
    ArrayList<Integer> size=new ArrayList<>();
    ArrayList<Integer> parent=new ArrayList<>();

    public DisjointSet(int n){
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

        if(ulp_u==ulp_v) return;
        if(size.get(ulp_u)<size.get(ulp_v)){
            parent.set(ulp_u,ulp_v);
            size.set(ulp_v,size.get(ulp_u)+size.get(ulp_v));
        }else{
            parent.set(ulp_v,ulp_u);
            size.set(ulp_u,size.get(ulp_u)+size.get(ulp_v));
        }
    }
}