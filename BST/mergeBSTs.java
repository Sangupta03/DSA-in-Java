package BST;

import java.util.*;
class mergeBSTs {
    public TreeNode canMerge(List<TreeNode> trees) {
        
        HashMap<Integer,TreeNode> rootMap=new HashMap<>();
        //map to roots for quick lookup
        HashSet<Integer> leafval=new HashSet<>();

        for(TreeNode tree:trees){
            rootMap.put(tree.val,tree);
            if(tree.left!=null) leafval.add(tree.left.val);
            if(tree.right!=null) leafval.add(tree.right.val);
            //since each bst contains 3 nodes we add children this way
        }

        TreeNode root=null;

        for(TreeNode node:rootMap.values()){
            if(!leafval.contains(node.val)){
                if(root!=null){
                    return null; //multiple diff main roots exist
                }
                root=node;
            }
        }
        if(root==null) return null;
        rootMap.remove(root.val);  //unmark root
        if(!canMergeAndValidate(root,rootMap,Long.MIN_VALUE,Long.MAX_VALUE)){
            return null;
        };
        return rootMap.isEmpty()?root:null;
    }

    public boolean canMergeAndValidate(TreeNode node,HashMap<Integer,TreeNode> hp,long minVal,long maxVal){
        if(node==null) return true;

        if(node.val<=minVal || node.val>=maxVal) return false;

        // If it's a leaf node, check if we can attach a matching sub-tree root
    
        if(node.left==null && node.right==null){
            if(hp.containsKey(node.val)){
                TreeNode child=hp.remove(node.val); //unmark from map
                node.left=child.left;
                node.right=child.right;
            }
        }
        
        //continue and validate left and right subtree
        return canMergeAndValidate(node.left,hp,minVal,node.val) && canMergeAndValidate(node.right,hp,node.val,maxVal);
    }
}