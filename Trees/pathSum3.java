import java.util.*;
class pathSum3 {
    HashMap<Long,Integer> hp=new HashMap<>();
    int cnt=0;
    public int pathSum(TreeNode root, int targetSum) {
        hp.put(0L,1);
        solve(root,targetSum,0L);
        return cnt;
    }

    public void solve(TreeNode node,int targetSum,long curr){
        if(node==null) return;
        curr+=node.val;
        long rem=curr-targetSum;

        if(hp.containsKey(rem)){
            cnt+=hp.get(rem);
        }
        hp.put(curr,hp.getOrDefault(curr,0)+1);
        solve(node.left,targetSum,curr);
        solve(node.right,targetSum,curr);
        hp.put(curr,hp.getOrDefault(curr,0)-1);
        
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