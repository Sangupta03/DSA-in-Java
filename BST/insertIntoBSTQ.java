package BST;

//recursive approach
class insertIntoBSTQ {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null) return new TreeNode(val);

        if(val<root.val){
            root.left=insertIntoBST(root.left,val);
        }else{
            root.right=insertIntoBST(root.right,val);
        }
        return root;
    }
}

//iterative approach

class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
       TreeNode curr=root;
       if(root==null){
        return root=new TreeNode(val);
       }
       while(true){
        if(curr.val>val){
            if(curr.left!=null) curr=curr.left;
            else{
                curr.left=new TreeNode(val);
                break;
            }
        }else{
            if(curr.right!=null) curr=curr.right;
            else{
               curr.right=new TreeNode(val);
               break; 
            }
        }
       }
       return root; 
    }
}