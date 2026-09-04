package Graphs;
import java.util.*;
class powerGridMaintenance {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DisjointSet ds=new DisjointSet(c+1);

        for(int[] connection:connections){
            int u=connection[0];
            int v=connection[1];
            ds.unionByRank(u,v);
        }

        boolean[] online=new boolean[c+1];
        Arrays.fill(online,true);

        ArrayList<Integer> ans=new ArrayList<>();
        Map<Integer,PriorityQueue<Integer>> mp=new HashMap<>();

        for(int i=1;i<=c;i++){
            int root=ds.findParent(i);
            mp.putIfAbsent(root,new PriorityQueue<>());
            mp.get(root).add(i);
        }

        for(int[] q:queries){
            int type=q[0];
            int x=q[1];

            if(type==2){
                online[x]=false;
            }
            else{
                if(online[x]){
                    ans.add(x);
                }

                else{
                    //node not online
                    //choose next minimum
                    int id=ds.findParent(x);
                    PriorityQueue<Integer> p=mp.get(id);

                    while(!p.isEmpty() && !online[p.peek()]){
                        p.poll();
                    }
                    if(p.isEmpty()){
                        ans.add(-1);
                    }else{
                        ans.add(p.peek());
                    }
                }
            }
        }
        int idx=0;
        int[] res=new int[ans.size()];
        for(int x:ans){
            res[idx++]=x;
        }
        return res;
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
        int ulp_v=findParent(v);
        int ulp_u=findParent(u);

        if(ulp_v==ulp_u) return;

        if(rank.get(ulp_u)<rank.get(ulp_v)){
            parent.set(ulp_u,ulp_v);
        }else if(rank.get(ulp_u)>rank.get(ulp_v)){
            parent.set(ulp_v,ulp_u);
        }else{
            parent.set(ulp_u,ulp_v);
            rank.set(ulp_v,rank.get(ulp_v)+1);
        }
    }
}
