import java.util.*;
class printAllNodesAtK {
    HashMap<TreeNode,TreeNode> parent=new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findParent(root,null);
        List<Integer> ans=new ArrayList<>();

        Queue<TreeNode> q=new LinkedList<>();
        HashSet<TreeNode> visited=new HashSet<>();
        q.offer(target);
        visited.add(target);
        int dist=0;

        while(!q.isEmpty()){
            int size=q.size();
            if(dist==k) break;
            for(int i=0;i<size;i++){
                TreeNode node=q.poll();
                if(node.left!=null && visited.add(node.left)){
                    q.offer(node.left);
                }
                if(node.right!=null && visited.add(node.right)){
                    q.offer(node.right);
                }
                TreeNode parentP=parent.get(node);
                if(parentP!=null && visited.add(parentP)){
                    q.offer(parentP);
                }
            }
            dist++;
        }

        while(!q.isEmpty()){
            ans.add(q.poll().val);
        }
        return ans;
    }

    public void findParent(TreeNode node,TreeNode par){
        if(node==null) return;
        parent.put(node,par);
        findParent(node.left,node);
        findParent(node.right,node);
    }

    //find parent mapping first, then u can do level by level bfs or dist by dist bfs from the target node
    //u can go in three ways-go left node,go right node, or go to parent node(up)
}