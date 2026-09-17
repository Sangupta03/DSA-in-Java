import java.util.*;

class BTConstPostInOrder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer,Integer> hp=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            hp.put(inorder[i],i);
        }
        int n=inorder.length;
        if(n==1) return new TreeNode(inorder[0]);
        return buildTree(inorder,0,n-1,postorder,0,n-1,hp);
    }

    public TreeNode buildTree(int[] inorder,int inStart,int inEnd,int[] postorder,int postStart,int postEnd,HashMap<Integer,Integer> hp){
        if(postStart>postEnd || inStart>inEnd) return null;
        TreeNode root=new TreeNode(postorder[postEnd]);
        int inRoot=hp.get(root.val);
        int numLeft=inRoot-inStart;

        root.left=buildTree(inorder,inStart,inRoot-1,postorder,postStart,postStart+numLeft-1,hp);
        root.right=buildTree(inorder,inRoot+1,inEnd,postorder,postStart+numLeft,postEnd-1,hp);
        return root;
    }
}