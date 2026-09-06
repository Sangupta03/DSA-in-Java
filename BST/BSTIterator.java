package BST;
import java.util.*;
class BSTIterator {

    Stack<TreeNode> stk;
    public BSTIterator(TreeNode root) {
        stk=new Stack<>();
        pushLeft(root);
    }
    public void pushLeft(TreeNode node){
        if(node==null) return;
        while(node!=null){
            
            stk.push(node);
            node=node.left;  //to find min val on left side
            
        }
    }
    
    public int next() {
        TreeNode temp=stk.pop();
        int val=temp.val;

        if(temp.right!=null){
            pushLeft(temp.right); //trying to find min val in right subtree
        }
        return val;
    }
    
    public boolean hasNext() {
        return !stk.isEmpty();
    }
}