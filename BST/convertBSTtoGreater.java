
class convertBSTtoGreater {
    int sum=0;
    // reverse inorder traversal since we need to find sum of bigger val and replace node val
    public TreeNode convertBST(TreeNode root) {
        if(root==null) return null;
        convertBST(root.right);
        sum+=root.val;
        root.val=sum;
        convertBST(root.left);
        return root;
    }
}