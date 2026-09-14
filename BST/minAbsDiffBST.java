package BST;

import java.util.*;
//use inorder
class minAbsDiffBST {
    public int getMinimumDifference(TreeNode root) {
        TreeNode curr=root;
        Stack<TreeNode> stk=new Stack<>();
       
        int minDiff=Integer.MAX_VALUE;
        TreeNode prev=null;

        while(curr!=null || !stk.isEmpty()){
            if(curr!=null){
                stk.push(curr);
                curr=curr.left;
            }else{
                TreeNode temp=stk.pop();
                if(prev!=null){
                    minDiff=Math.min(minDiff,temp.val-prev.val);
                }
                prev=temp;
                curr=temp.right;
            }
        }
        return minDiff;
    }
}


class Solution {
    int ans=Integer.MAX_VALUE;
    int prev=-1;
        
    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return ans;
        
        
    }
    public void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        if(prev!=-1)
        {
            ans=Math.min(ans,Math.abs(root.val-prev));
        }
        prev=root.val;
        inorder(root.right);
    }
}