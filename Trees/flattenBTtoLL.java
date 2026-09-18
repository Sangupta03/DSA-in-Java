import java.util.*;

//approach 1-using stack and preorder traversal
//time and space is O(n);

class flattenBTtoLL {
    public void flatten(TreeNode root) {
        
        if(root==null) return;
        Stack<TreeNode> stk=new Stack<>();
        stk.push(root);

        while(!stk.isEmpty()){
            TreeNode curr=stk.pop();
            if(curr.right!=null) stk.push(curr.right);
            if(curr.left!=null) stk.push(curr.left);

            if(!stk.isEmpty()){
                curr.right=stk.peek();
            }
            curr.left=null;
        }
    }
}

//approach 2- optimal using morris traversal

class Solution {
    public void flatten(TreeNode root) {
        
        if(root==null) return;
        TreeNode curr=root;

        while(curr!=null){
            if(curr.left!=null){
                TreeNode prev=curr.left;
                while(prev.right!=null){
                    prev=prev.right;
                }
                prev.right=curr.right;
                curr.right=curr.left;
                curr.left=null;
            }
            curr=curr.right;
        }
    }
}