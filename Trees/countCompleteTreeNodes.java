class countCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if(root==null) return 0;
        int leftCnt=leftHCnt(root);
        int rightCnt=rightHCnt(root);
        if(leftCnt==rightCnt) return ((2<<(leftCnt))-1);
        else return 1+countNodes(root.right)+countNodes(root.left);
    }

    int leftHCnt(TreeNode node){
        if(node==null) return 0;
        int n=0;
        while(node.left!=null){
            n++;
            node=node.left;
        }
        return n;
    }
    int rightHCnt(TreeNode node){
        if(node==null) return 0;
        int n=0;
        while(node.right!=null){
            n++;
            node=node.right;
        }
        return n;
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