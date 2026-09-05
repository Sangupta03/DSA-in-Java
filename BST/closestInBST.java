package BST;
class closestInBST {
    public int minDiff(Node root, int k) {
        int closest=root.data;
        
        while(root!=null){
            if(Math.abs(root.data-k)<Math.abs(closest-k)){
                closest=root.data;
            }
            if(k<root.data){
                root=root.left;
            }else{
                root=root.right;
            }
        }
        return Math.abs(closest-k);
    }
}
class Node{
  Node left;
  Node right;
  int data;
  Node(int data){
    this.data=data;
  }
  Node(Node left,Node right,int data){
    this.left=left;
    this.right=right;
    this.data=data;
  }
}