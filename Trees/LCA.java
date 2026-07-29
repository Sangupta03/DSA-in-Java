class LCA {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q) return root; //simply return node;

        TreeNode left=lowestCommonAncestor(root.left,p,q);//leftchild
        TreeNode right=lowestCommonAncestor(root.right,p,q); //rightchild;
        if(left!=null && right!=null) return root; //found LCA
        return left!=null?left:right;  //returns the node p or q found info
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