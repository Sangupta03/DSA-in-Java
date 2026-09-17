import java.util.*;
 //we can do level order/ pre/post or inorder traversals here
public class serializeAndDeserialize {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) return "";
        StringBuilder sb=new StringBuilder();
        Queue<TreeNode> q = new java.util.LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            TreeNode node=q.poll();
            if(node==null){
                sb.append("n ");
                continue;
            }
            sb.append(node.val+" ");
            
            q.offer(node.left);
            q.offer(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if(data.isEmpty()) return null;
        String[] arr=data.split(" ");
        Queue<TreeNode> q=new ArrayDeque<>();
        TreeNode root=new TreeNode(Integer.parseInt(arr[0]));
        q.offer(root);

        for(int i=1;i<arr.length;i++){
            TreeNode parent=q.poll();
            if(!arr[i].equals("n")){
                TreeNode left=new TreeNode(Integer.parseInt(arr[i]));
                parent.left=left;
                q.offer(left);
            }
            i++;

            if(i<arr.length && !arr[i].equals("n")){
                TreeNode right=new TreeNode(Integer.parseInt(arr[i]));
                parent.right=right;
                q.offer(right);
            }
        }
        return root;
    }
}