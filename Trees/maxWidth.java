import java.util.*;
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        long maxi=0;
        Queue<Pair> q=new ArrayDeque<>();
        q.offer(new Pair(0,root));

        while(!q.isEmpty()){
            int size=q.size();
            long first=0;
            long last=0;
            long min=q.peek().idx;
            for(int i=0;i<size;i++){
                Pair p=q.poll();
                long id=p.idx-min; //to convert idx num to 1..n based on each level
                if(i==0) first=id;
                if(i==size-1) last=id;

                if(p.node.left!=null) q.offer(new Pair(2*id+1,p.node.left));
                if(p.node.right!=null) q.offer(new Pair(2*id+2,p.node.right));
            }
            maxi=Math.max(maxi,last-first+1);
        }
        return (int)maxi;
    }
}
class Pair{
    long idx;
    TreeNode node;
    Pair(long idx,TreeNode node){
        this.idx=idx;
        this.node=node;
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
