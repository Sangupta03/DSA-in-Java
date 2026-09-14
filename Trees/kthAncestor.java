import java.util.*;

class kthAncestor {
    public int kthAncestorQ(Node root, int k, int node) {
       
        List<Integer> path=new ArrayList<>();
        
        findPath(root,path,node);
        //run a dfs check to find root to target path
        int idx=path.size()-1-k;
        //transform k to idx from root instead
        //k was the ancestor value from target
        if(idx>=0) return path.get(idx);
        else return -1;
    }
    
    public boolean findPath(Node node,List<Integer> path,int target){
        
        if(node==null) return false;
        path.add(node.data);  //add to path
        
        if(node.data==target) return true;
        
        if(findPath(node.left,path,target) || findPath(node.right,path,target)){
            return true;
        }//target found so stop dfs 
        
        path.remove(path.size()-1); //backtrack
        return false;
    }
}
class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int val) {
        data = val;
        left = right = null;
    }
}