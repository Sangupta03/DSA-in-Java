import java.util.*;
//using bfs
class leftView {
    public ArrayList<Integer> leftViewQ(Node root) {
        // code here
        ArrayList<Integer> ans=new ArrayList<>();
        if(root==null) return ans;
        Queue<Node> q=new ArrayDeque<>();
        
        q.offer(root);
        
        while(!q.isEmpty()){
            int size=q.size();
            
            for(int i=0;i<size;i++){
                Node curr=q.poll();
                if(i==0) ans.add(curr.data);
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
            }
        }
        return ans;
    }
}

//using dfs
class Solution {
    public ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> ans=new ArrayList<>();
        if(root==null) return ans;
        dfs(root,ans,0);
        return ans;
    }
    
    public void dfs(Node node,ArrayList<Integer> ans,int level){
        if(node==null) return;
        
        if(level==ans.size()){
            ans.add(node.data);
        }
        
        dfs(node.left,ans,level+1);
        dfs(node.right,ans,level+1);
    }
}
class Node{
  Node left;
  Node right;
  int data;
  Node(int val){
    val=data;
    left=right=null;
  }
}