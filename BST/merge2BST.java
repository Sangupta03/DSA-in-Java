
//approach 1
import java.util.*;
class merge2BST{
    public ArrayList<Integer> merge(Node r1, Node r2) {
        ArrayList<Integer> t1=new ArrayList<>();
        ArrayList<Integer> t2=new ArrayList<>();
        
        inorder(r1,t1);
        inorder(r2,t2);
        ArrayList<Integer> ans=new ArrayList<>();
        int i=0;
        int j=0;
        
        while(i<t1.size() && j<t2.size()){
            if(t1.get(i)<=t2.get(j)){
                ans.add(t1.get(i));
                i++;
            }else{
                ans.add(t2.get(j));
                j++;
            }
        }
        while(i<t1.size()){
            ans.add(t1.get(i));
            i++;
        }
        while(j<t2.size()){
            ans.add(t2.get(j));
            j++;
        }
        return ans;
        
    }
    
    public void inorder(Node node,ArrayList<Integer> in){
        if(node==null) return;
        inorder(node.left,in);
        in.add(node.data);
        inorder(node.right,in);
    }
  
} //TC is O(n+m) and SC is O(n+m)

//approach 2 can space optimise and use 2 stacks SC(O(H1+H2));
//iterative inorder traversal

class Solution {
    public ArrayList<Integer> merge(Node r1, Node r2) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        
        // Initialize stacks with the leftmost paths of both trees
        pushLeft(r1, s1);
        pushLeft(r2, s2);
        
        // Traverse and merge
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (s1.isEmpty()) {
                Node curr = s2.pop();
                ans.add(curr.data);
                pushLeft(curr.right, s2);
            } else if (s2.isEmpty()) {
                Node curr = s1.pop();
                ans.add(curr.data);
                pushLeft(curr.right, s1);
            } else {
                // Both stacks have elements, compare the top nodes
                if (s1.peek().data <= s2.peek().data) {
                    Node curr = s1.pop();
                    ans.add(curr.data);
                    pushLeft(curr.right, s1);
                } else {
                    Node curr = s2.pop();
                    ans.add(curr.data);
                    pushLeft(curr.right, s2);
                }
            }
        }
        
        return ans;
    }
    
    // Helper function to push all left children of a node onto a stack
    private void pushLeft(Node node, Stack<Node> s) {
        while (node != null) {
            s.push(node);
            node = node.left;
        }
    }
}