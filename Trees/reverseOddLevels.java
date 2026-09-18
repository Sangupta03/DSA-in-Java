import java.util.*;

//using bfs
class reverseOddLevels {
    public TreeNode reverseOddLevelsQ(TreeNode root) {
        Queue<TreeNode> q=new ArrayDeque<>();
        int level=0;
        q.offer(root);

        while(!q.isEmpty()){
            int size=q.size();
            List<TreeNode> currLevel=new ArrayList<>();
            
            for(int i=0;i<size;i++){
                TreeNode node=q.poll();
                currLevel.add(node);
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
            }
           
            if(level%2==1){
                int left=0;
                int right=currLevel.size()-1;
                while(left<right){
                    int temp=currLevel.get(left).val;
                    currLevel.get(left).val=currLevel.get(right).val;
                    currLevel.get(right).val=temp;
                    left++;
                    right--;
                }
            }
            level++;
        }
        return root;
    }
}

//using dfs OPTIMAL SPACE COMPLEXITY O(LOG N), tc-O(N)

class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        // Start DFS passing the left and right children of the root (Level 1, which is odd)
        dfs(root.left,root.right,1);
        return root;
    }

    public void dfs(TreeNode leftNode,TreeNode rightNode,int level){
        if(leftNode==null || rightNode==null) return;

        if(level%2==1){
            int temp=leftNode.val;
            leftNode.val=rightNode.val;
            rightNode.val=temp;
        }
        // Move to the next level. 
        // We pair the "outside" nodes together and the "inside" nodes together.
        dfs(leftNode.left,rightNode.right,level+1);
        dfs(leftNode.right,rightNode.left,level+1);
    }
}