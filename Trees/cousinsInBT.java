import java.util.*;
//BRUTE FORCE APPROACH

class cousinsInBT {
    HashMap<TreeNode,TreeNode> parent=new HashMap<>();

    public boolean isCousins(TreeNode root, int x, int y) {
        if(root==null) return false;
        findparent(root,null);

        HashMap<Integer,Tuple> hp=new HashMap<>();

        Queue<TreeNode> q=new ArrayDeque<>();
        q.offer(root);
        int level=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                TreeNode node=q.poll();
                hp.put(node.val,new Tuple(parent.get(node),level));
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
            }
            level++;
        }
        if(hp.get(x).level==hp.get(y).level && hp.get(x).parent!=hp.get(y).parent){
            return true;
        }
        return false;

    }

    public void findparent(TreeNode node,TreeNode par){
        if(node==null) return;
        parent.put(node,par);
        findparent(node.left,node);
        findparent(node.right,node);
    }
}
class Tuple{
    TreeNode parent;
    int level;
    
    Tuple(TreeNode parent,int level){
        this.level=level;
        this.parent=parent;
    }
}

//Optimal approach
//USE DFS FOR ONLY FINDING X AND Y AND THEIR PARENTS
class Solution {
    int xLevel=-1;
    int yLevel=-2;
    TreeNode xparent=null;
    TreeNode yparent=null;

    public boolean isCousins(TreeNode root, int x, int y) {
        if(root==null) return false;
        solveDfs(root,null,x,y,0);
        if(xLevel==yLevel && xparent!=yparent){
            return true;
        }
        return false;
    }

    public void solveDfs(TreeNode node,TreeNode parent,int x,int y,int level){
        if(node==null) return;

        if(node.val==x){
            xLevel=level;
            xparent=parent;
        }

        if(node.val==y){
            yLevel=level;
            yparent=parent;
        }

        if(xparent!=null && yparent!=null) return; //early stopping

        solveDfs(node.left,node,x,y,level+1);
        solveDfs(node.right,node,x,y,level+1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}