class recoverBST {
    TreeNode first=null;
    TreeNode second=null;
    TreeNode prev=null;
    public void recoverTree(TreeNode root) {
        if(root==null) return;
        inorder(root);
        int temp=first.val;
        first.val=second.val;
        second.val=temp;
    }
    public void inorder(TreeNode node){
        if(node==null) return;
        inorder(node.left);
        if(prev!=null && node.val<prev.val){
            if(first==null){
                first=prev;  //first bigger value dip caught
            }
            second=node;  //second dip of smaller val caught
        }
        prev=node;
        inorder(node.right);
    }
}