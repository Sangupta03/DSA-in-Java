package BST;
import java.util.*;
class twoSum4 {
    HashSet<Integer> hs=new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if(root==null) return false;
        

        if(hs.contains(k-root.val)) return true;
        hs.add(root.val);
        return findTarget(root.left,k) || findTarget(root.right,k);
    }
}
//solve it with inorder and normal two sum approach

class Solution {
    
    public boolean findTarget(TreeNode root, int k) {
        if(root==null) return false;
        ArrayList<Integer> inorder=new ArrayList<>();
        Stack<TreeNode> stk=new Stack<>();
        TreeNode node=root;

        while(node!=null || !stk.isEmpty()){
            if(node!=null){
                stk.push(node);
                node=node.left;
            }else{
                TreeNode temp=stk.pop();
                inorder.add(temp.val);
                node=temp.right;
            }
        }
        int i=0;
        int j=inorder.size()-1;

        while(i<j){
            int sum=inorder.get(i)+inorder.get(j);
            if(sum<k){
                i++;
            }else if(sum>k){
                j--;
            }else{
                return true;
            }
        }
        return false;
    }
}