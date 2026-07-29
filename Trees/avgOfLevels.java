import java.util.*;
class avgOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans=new ArrayList<>();
        Queue<TreeNode> q=new ArrayDeque<>();
        if(root==null) return ans;

        q.offer(root);
        while(!q.isEmpty()){
            int size=q.size();
            double avg=0.0;
            for(int i=0;i<size;i++){
                TreeNode curr=q.poll();
                avg+=curr.val;
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
            }
            avg=avg/size;
            ans.add(avg);
        }
        return ans;
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
