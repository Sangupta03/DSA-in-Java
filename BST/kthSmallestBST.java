package BST;
import java.util.*;
class kthSmallestBST {
    public int kthSmallest(TreeNode root, int k) {
        //we have used the inorder traversal approach here
        Stack<TreeNode> stk=new Stack<>();
        TreeNode curr=root;

        while(curr!=null || !stk.isEmpty()){
            if(curr!=null){
                stk.push(curr);
                curr=curr.left;
            }else{
                k=k-1;
                TreeNode temp=stk.pop();
                if(k==0) return temp.val;
                curr=temp.right;
            }
        }
        return -1;
    }
}