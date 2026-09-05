package BST;
class TreeNode{
  TreeNode right;
  TreeNode left;
  int val;
  TreeNode(int val,TreeNode left,TreeNode right){
    this.val=val;
    this.left=left;
    this.right=right;
  }
  TreeNode(int val){
    this.val=val;
  }
}
class convertSortedArrToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        int n=nums.length;
        if(n==0 || nums==null) return null;
        return solve(nums,0,n-1);
    }
    //take the middle ele as root and recursively build left and right subtrees
    // from remaining left and right part of th arr
    public TreeNode solve(int[] nums,int l,int r){
        if(l>r) return null;
        int mid=l+(r-l)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=solve(nums,l,mid-1);
        root.right=solve(nums,mid+1,r);
        return root;
    }
}