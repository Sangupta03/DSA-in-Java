class Solution {
    int maxi=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        solve(root);
        return maxi;
    }

    public int solve(TreeNode node){
        if(node==null) return 0;
        int leftSum=Math.max(0,solve(node.left));
        int rightSum=Math.max(0,solve(node.right));
        maxi=Math.max(maxi,leftSum+rightSum+node.val);
        return node.val+Math.max(leftSum,rightSum);
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