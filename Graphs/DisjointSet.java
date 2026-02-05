package Graphs;
import java.util.*;

public class DisjointSet {
  ArrayList<Integer> rank = new ArrayList<>();
  ArrayList<Integer> parent = new ArrayList<>();

  public DisjointSet(int n){
    for(int i=0;i<=n;i++){
      rank.add(0);
      parent.add(i);
    }
  }

  public int findUParent(int node){
    if(node == parent.get(node)) return node;
    int uPar = findUParent(parent.get(node));
    parent.set(node, uPar);
    return parent.get(node);
  }

  public void unionByRank(int u,int v){
    int ulp_u = findUParent(u);
    int ulp_v = findUParent(v);

    if(rank.get(ulp_u) < rank.get(ulp_v)){
      parent.set(ulp_u, ulp_v);
    } else if(rank.get(ulp_v) < rank.get(ulp_u)){
      parent.set(ulp_v, ulp_u);
    } else {
      parent.set(ulp_v, ulp_u);
      rank.set(ulp_u, rank.get(ulp_u)+1);
    }
  }

  public static void main(String[] args) {
    DisjointSet ds = new DisjointSet(7);

    ds.unionByRank(1,2);
    ds.unionByRank(2,3);
    ds.unionByRank(4,5);
    ds.unionByRank(6,7);
    ds.unionByRank(5,6);

    System.out.println(ds.findUParent(7)==ds.findUParent(3) ? "Same" : "Not same");

    ds.unionByRank(3,7);

    System.out.println(ds.findUParent(7)==ds.findUParent(3) ? "Same" : "Not same");
  }
}

