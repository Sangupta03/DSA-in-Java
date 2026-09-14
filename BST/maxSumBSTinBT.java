package BST;

//we have to find sum of each subtree, as well as we have to validate if it is a BST or not
//we have to maintain a maxsum
//create a sentinal or small int[] arr that contains the sum, isbst(0/1),minval,maxval
class Solution {
    int maxSum=0;
    public int maxSumBST(TreeNode root) {
        if(root==null) return 0;
        dfs(root);
        return maxSum;
    }

    public int[] dfs(TreeNode node){
        if(node==null) return new int[]{1,Integer.MAX_VALUE,Integer.MIN_VALUE,0};//valid bst
        // returns {isBST(1/0), subtreeMin, subtreeMax, subtreeSum}
        int[] lhs=dfs(node.left);
        //for left subtree
        int[] rhs=dfs(node.right);
        //for right subtree

        //doing a postorder traversal here
        if(lhs[0]==1 && rhs[0]==1 && node.val>lhs[2] && node.val<rhs[1]){
            //check if valid bst
            int sum=lhs[3]+rhs[3]+node.val;
            maxSum=Math.max(sum,maxSum);
            return new int[]{1,Math.min(node.val,lhs[1]),Math.max(node.val,rhs[2]),sum};
        }
        return new int[]{0,0,0,0}; //default ans;
    }
}