import java.util.*;
//using bfs
class rightView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans=new ArrayList<>();
        if(root==null) return ans;

        Queue<TreeNode> q=new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size=q.size();
            int val=q.size()-1;

            for(int i=0;i<size;i++){
                TreeNode node=q.poll();
                if(i==val) ans.add(node.val);
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
            }
        }
        return ans;
    }
}

//using dfs same approach as left view
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
