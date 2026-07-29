 //0->not covered;
 //1->covered;
 //2->camera here
class BTcameras {
    int camera=0;
    public int minCameraCover(TreeNode root) {
        if(root==null) return 0;
        if(solve(root)==0){
            camera++; //to cover the parent
        }
        return camera;
    }

    public int solve(TreeNode node){
        if(node==null) return 1;  //null = "covered" so leaves read as uncovered

        int l=solve(node.left);
        int r=solve(node.right);
        if(l==0 || r==0){
            camera++;
            return 2;
        }else if(l==2 || r==2){
            return 1; //parent will be covered
        }
        return 0; //parent not covered
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