package BST;

class deleteNodeBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) return null;

        if(root.val>key){
            root.left=deleteNode(root.left,key);
        }else if(root.val<key){
            root.right=deleteNode(root.right,key);
        }else{
            //only one child
            if(root.left==null) return root.right; //case 1
            if(root.right==null) return root.left; //case 2;
            //case 3 both the children exist
            //root is the val to delete
            TreeNode succ=root.right; //next node to replace the del one will be min node in the right subtree

            while(succ.left!=null) succ=succ.left;
            root.val=succ.val; //put min val at root
            //delete the extra min val
            root.right=deleteNode(root.right,succ.val);
        }
        return root;
    }
}