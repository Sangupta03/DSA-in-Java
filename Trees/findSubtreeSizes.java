import java.util.*;
class findSubtreeSizes {
    public int[] findSubtreeSizesQ(int[] parent, String s) {
        int[] ans=new int[parent.length];
        int[] lastSeen=new int[26];

        Arrays.fill(ans,1);
        Arrays.fill(lastSeen,-1);
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();

        for(int i=0;i<parent.length;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=1;i<parent.length;i++){
            int u=parent[i];
            //u-->node
            adj.get(u).add(i);
        }
        dfs(0,parent,s,ans,lastSeen,adj);
        return ans;
    }

    public void dfs(int node,int[] parent,String s,int[] ans,int[] lastSeen,ArrayList<ArrayList<Integer>> adj){

        int c=s.charAt(node)-'a';
        int lastAncestor=lastSeen[c];

        lastSeen[c]=node;

        for(int child:adj.get(node)){
            dfs(child,parent,s,ans,lastSeen,adj);
        }

        lastSeen[c]=lastAncestor; //lastSeen ancestor for curr node

        int newParent=parent[node];

        if(node!=0){
            if(lastAncestor!=-1){
                newParent=lastAncestor;
            }
            parent[node]=newParent;
            ans[newParent]+=ans[node];
        }

    }
}