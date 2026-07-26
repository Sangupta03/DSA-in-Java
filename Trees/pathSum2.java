package Trees;
import java.util.*;

class pathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res=new ArrayList<>();
        solve(root,targetSum,res,new ArrayList<>());
        return res;
    }

    public void solve(TreeNode node,int sum,List<List<Integer>> res,List<Integer> path){
        if(node==null) return;
        path.add(node.val);//choose
        if(node.left==null && node.right==null){
            if(node.val==sum){
                res.add(new ArrayList<>(path));
            }
        }else{
            solve(node.left,sum-node.val,res,path);
            solve(node.right,sum-node.val,res,path);
        }
        path.remove(path.size()-1);  //backtack;
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