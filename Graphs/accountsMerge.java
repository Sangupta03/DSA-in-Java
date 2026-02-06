package Graphs;
import java.util.*;

class mergeAccounts {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n=accounts.size();
        HashMap<String,Integer> hp=new HashMap<>();

        DisjointSet ds=new DisjointSet(n);
        for(int i=0;i<n;i++){
            for(int j=1;j<accounts.get(i).size();j++){
                if(!hp.containsKey(accounts.get(i).get(j))){
                    hp.put(accounts.get(i).get(j),i);
                }else{
                    ds.unionBySize(i,hp.get(accounts.get(i).get(j)));
                }
            }
        }
        ArrayList<String>[] mailNodes=new ArrayList[n];

        for(int i=0;i<n;i++){
            mailNodes[i]=new ArrayList<>();
        }
        for(Map.Entry<String,Integer> it: hp.entrySet()){
            int node=ds.findParent(it.getValue());
            String mail=it.getKey();
            mailNodes[node].add(mail);        
        }
        List<List<String>> ans=new ArrayList<>();
        
        for(int i=0;i<n;i++){
            List<String> temp=new ArrayList<>();
            if(mailNodes[i].size()==0) continue;
            Collections.sort(mailNodes[i]);
            temp.add(accounts.get(i).get(0));
            for(String s:mailNodes[i]){
                temp.add(s);
            }
            ans.add(temp);
        }
        return ans;
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
        if(node==parent.get(node)){
            return node;
        }
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
            size.set(ulp_v,size.get(ulp_v)+size.get(ulp_u));
        }else{
            parent.set(ulp_v,ulp_u);
            size.set(ulp_u,size.get(ulp_v)+size.get(ulp_u));
        }
    }
