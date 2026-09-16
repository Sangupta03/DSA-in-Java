import java.util.*;
class BTConstPreorderPostOrder {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if(preorder.length==1) return new TreeNode(preorder[0]);
        HashMap<Integer,Integer> hp=new HashMap<>();

        for(int i=0;i<postorder.length;i++){
            hp.put(postorder[i],i);  //map ele to idx
        }
        int n=preorder.length;
        return buildTree(preorder,0,n-1,postorder,0,n-1,hp);
    }

    public TreeNode buildTree(int[] preorder,int preStart,int preEnd,int[] postorder,int postStart,int postEnd,HashMap<Integer,Integer> hp){

        if(preStart>preEnd || postStart>postEnd) return null;
        
        TreeNode root=new TreeNode(preorder[preStart]);

        if(preStart==preEnd){
            return root;
        }
        int leftroot=hp.get(preorder[preStart + 1]);
        //idx of left root

        int numleft=leftroot-postStart+1;
        root.left=buildTree(preorder,preStart+1,preStart+numleft,postorder,postStart,leftroot,hp);

        root.right=buildTree(preorder,preStart+numleft+1,preEnd,postorder,leftroot+1,postEnd-1,hp);
        return root;
    }
}