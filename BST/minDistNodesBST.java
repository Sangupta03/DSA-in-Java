package BST;//VERY IMP
//FIRST FIND THE lca OF THE NODES
//THEN FIND DISTANCE OF EACH NODE FROM THE LCA

class minDistNodesBST {
    public int findDist(Node root, int a, int b) {
        if(root==null) return 0;
        Node lca=findLCA(root,a,b);
        int ans=findDepth(lca,a)+findDepth(lca,b);
        return ans;
    }
    public int findDepth(Node node,int target){
        if(node==null) return -1;
        if(node.data==target) return 0;
        int left=findDepth(node.left,target);
        if(left!=-1) return left+1;
        int right=findDepth(node.right,target);
        if(right!=-1) return right+1;
        return -1;
    }
    
    public Node findLCA(Node root,int a,int b){
        if(root==null || root.data==a || root.data==b){
            return root;
        }
        Node left=findLCA(root.left,a,b);
        Node right=findLCA(root.right,a,b);
        if(left!=null && right!=null){
            return root; //both a and b found
        }
        if(left!=null) return left;
        else return right;
    }
}