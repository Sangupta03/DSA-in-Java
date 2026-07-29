import java.util.*;
class bottomView {
    
    public ArrayList<Integer> bottomViewQ(Node root) {
        
        ArrayList<Integer> ans=new ArrayList<>();
        if(root==null) return ans;
        Queue<Pair> q=new ArrayDeque<>();
        TreeMap<Integer,Integer> map=new TreeMap<>(); //used treemap cause it sorts
        //col,val
        q.offer(new Pair(0,root));
        
        while(!q.isEmpty()){
            Pair p=q.poll();
            int col=p.col;
            Node node=p.node;
            
            map.put(col,node.data); //overwrite previous values
            
            if(node.left!=null) q.offer(new Pair(col-1,node.left));
            if(node.right!=null) q.offer(new Pair(col+1,node.right));
        }
        
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            ans.add(entry.getValue());
        }
        return ans;
        
    }
}
class Pair{
    int col;
    Node node;
    Pair(int col,Node node){
        this.col=col;
        this.node=node;
    }
}
class Node{
  int data;
  Node right,left;
  Node(int val){
    val=data;
    left=right=null;
  }
}