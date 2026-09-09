
class constructBSTFromPreorder {
    int idx=0;
    public TreeNode bstFromPreorder(int[] preorder) {
        int n=preorder.length;
        if(n==0 || preorder==null) return null;
        return buildTree(preorder,Integer.MAX_VALUE);
        //build tree range bound
    }
    public TreeNode buildTree(int[] preorder,int bound){
        if(idx==preorder.length || bound<preorder[idx]) return null;// value belongs higher up
        TreeNode node=new TreeNode(preorder[idx++]);
        node.left=buildTree(preorder,node.val); //left range is less than root val
        node.right=buildTree(preorder,bound); //right range is inherited ,greater than root val
        return node;
    }
}