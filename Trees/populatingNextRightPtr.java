import java.util.*;
class populatingNextRightPtr {
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                
                // If this is not the last node in the current level, point to the next one in the queue
                if (i < size - 1) {
                    node.next = q.peek();
                }
                
                // Add children uniformly
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }
        return root;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}


//optimal approach
class Solution {
    public Node connect(Node root) {
        if(root==null) return root;

        Node level=root;
        
        while(level.left!=null){
            Node curr=level;

            while(curr!=null){
                curr.left.next=curr.right; //connect siblings

                if(curr.next!=null){
                    curr.right.next=curr.next.left; //connect cousins
                }

                curr=curr.next;
            }
            level=level.left;
        }
        return root;
    }
}