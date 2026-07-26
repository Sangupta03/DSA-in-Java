package Trees;

class balancedBT {
    
    public boolean isBalanced(TreeNode root) {
        return check(root)!=-1;
    }
    public int check(TreeNode root) {
        if(root==null) return 0;
        int leftH=check(root.left);
        if(leftH==-1) return -1;
        int rightH=check(root.right);
        if(rightH==-1) return -1;
        if(Math.abs(leftH-rightH)>1) return -1;
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
