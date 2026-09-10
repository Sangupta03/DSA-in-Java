class countNodesEqualToAvg{
    int ans=0; //returns the no. of nodes equal to avg of subtree
    
    public int averageOfSubtree(TreeNode root) {
        if(root==null){
            return 0;
        }
        postOrder(root);
        return ans;
    }

    //postorder since we need info about left subtree and rightsubtree for a given node
    public Pair postOrder(TreeNode node){
        if(node==null){
            return new Pair(0,0);
        }
        Pair left=postOrder(node.left);
        Pair right=postOrder(node.right);
        int sum=left.sum+right.sum+node.val;
        int cnt=left.cnt+right.cnt+1;

        if(node.val==(sum/cnt)){
            ans++;
        }
        return new Pair(sum,cnt);
    }
}
class Pair{
    int sum;
    int cnt;
    Pair(int sum,int cnt){
        this.sum=sum;
        this.cnt=cnt;
    }
}