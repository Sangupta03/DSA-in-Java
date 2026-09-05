package BST;
import java.util.*;

class predSuccInBST {
    public ArrayList<Node> findPreSuc(Node root, int key) {
        // code here
        ArrayList<Node> ans=new ArrayList<>();
        
        if(root==null){
            return ans;
        }
        Node pred=predSolve(root,key);
        Node succ=succSolve(root,key);
        ans.add(pred);
        ans.add(succ);
        return ans;
    }
    
    public Node predSolve(Node node,int key){
        
        Node pred=null;
        while(node!=null){
            if(key>node.data){
                pred=node;
                node=node.right; //try to find more bigger val
            }else{
                node=node.left;
            }
        }
        return pred;
    }
    
    public Node succSolve(Node node,int key){
        Node succ=null;
        while(node!=null){
            if(key<node.data){
                succ=node;
                node=node.left;
            }else{
                node=node.right;
            }
        }
        return succ;
    }
    
}