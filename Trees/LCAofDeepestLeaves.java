
class LCAofDeepestLeaves {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root==null) return root;
        return dfs(root).node;
    }
    public Pair dfs(TreeNode node){
        if(node==null) return new Pair(null,0);

        Pair left=dfs(node.left);
        Pair right=dfs(node.right);
        if(left.depth==right.depth) return new Pair(node,1+left.depth);
        //balanced, LCA is node
        if(left.depth<right.depth){
            return new Pair(right.node,right.depth+1); // deeper side wins, carry its LCA up
        }else{
            return new Pair(left.node,left.depth+1);
        } 
    }
}
class Pair{
    TreeNode node;
    int depth;
    Pair(TreeNode node,int depth){
        this.node=node;
        this.depth=depth;
    }
}