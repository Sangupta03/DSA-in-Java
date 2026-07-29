import java.util.*;
class printBoundary {
    public ArrayList<Integer> ans=new ArrayList<>();
    public ArrayList<Integer> boundaryTraversal(Node root) {
        // code here
        if(root==null) return new ArrayList<>();
        if(!isLeaf(root)) ans.add(root.data);
        leftBoundary(root);
        addLeaves(root);
        rightBoundary(root);
        return ans;
    }
    public boolean isLeaf(Node node){
        if(node.left==null && node.right==null){
            return true;
        }
        return false;
    }
    public void addLeaves(Node node){
        if(node==null) return;
        if(isLeaf(node)){
            ans.add(node.data);
        }
        addLeaves(node.left);
        addLeaves(node.right);
    }
    public void leftBoundary(Node node){
        if(node==null) return;
        Node curr=node.left;
        
        while(curr!=null){
            if(!isLeaf(curr)){
                ans.add(curr.data);
            }
            if(curr.left!=null){
                curr=curr.left;
            }else{
                curr=curr.right;
            }
        }
    }
    public void rightBoundary(Node node){
        if(node==null) return;
        Node curr=node.right;
        ArrayList<Integer> temp=new ArrayList<>();
        
        while(curr!=null){
            if(!isLeaf(curr)){
                temp.add(curr.data);
            }
            if(curr.right!=null){
                curr=curr.right;
            }else{
                curr=curr.left;
            }
        }
        for(int i=temp.size()-1;i>=0;i--){
            ans.add(temp.get(i));
        }
    }
}
class Node{
  Node left;
  Node right;
  int data;
  Node(int val){
    val=data;
    left=right=null;
  }
}