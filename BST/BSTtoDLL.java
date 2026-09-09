
class Node {
  public int data;
  public Node left, right;

  public Node(int x) {
      data = x;
      left = right = null;
  }
}
class BSTtoDLL {
    Node head=null;
    Node prev=null;
    public Node treeToDLL(Node root) {
        if(root==null) return null;
        inorder(root);
        
        return head;
    }
    //inorder traversal
    public void inorder(Node node){
        if(node==null) return;
        inorder(node.left);
        if(prev==null) head=node;
        else{
            prev.right=node;
            node.left=prev;
        }
        prev=node;
        inorder(node.right);
    }
}