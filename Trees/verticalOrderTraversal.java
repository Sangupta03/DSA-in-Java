import java.util.*;
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        if(root==null) return ans;
        TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> map=new TreeMap<>();

        Queue<Tuple> q=new ArrayDeque<>();
        q.offer(new Tuple(0,0,root));

        while(!q.isEmpty()){
            Tuple curr=q.poll();
            TreeNode node=curr.node;
            int col=curr.col;
            int row=curr.row;

            if(!map.containsKey(col)){
                map.put(col,new TreeMap<>());
            }
            if(!map.get(col).containsKey(row)){
                map.get(col).put(row,new PriorityQueue<>());
            }
            map.get(col).get(row).add(node.val);
            if(node.left!=null) q.offer(new Tuple(row+1,col-1,node.left));
            if(node.right!=null) q.offer(new Tuple(row+1,col+1,node.right));
        }
        //build ans

        for(TreeMap<Integer,PriorityQueue<Integer>> mp:map.values()){
            List<Integer> temp=new ArrayList<>();
            for(PriorityQueue<Integer> pq:mp.values()){
                while(!pq.isEmpty()){
                    temp.add(pq.poll());
                }
            }
            ans.add(temp);
        }
        return ans;
    }
}
class Tuple{
    int col;
    int row;
    TreeNode node;
    Tuple(int row,int col,TreeNode node){
        this.col=col;
        this.row=row;
        this.node=node;
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