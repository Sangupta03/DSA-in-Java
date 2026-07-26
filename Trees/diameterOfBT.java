class diameterOfBT {
    int diameter=0;
    public int diameterOfBinaryTree(TreeNode root) {
        solve(root);
        return diameter;
    }

    public int solve(TreeNode root){
        if(root==null) return 0;
        int leftH=solve(root.left);
        int rightH=solve(root.right);
        diameter=Math.max(diameter,leftH+rightH);
        return 1+Math.max(leftH,rightH);
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
