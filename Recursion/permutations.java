package Recursion;
import java.util.*;

class permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> ds=new ArrayList<>();
        boolean[] used=new boolean[nums.length];
        solve(nums,ans,ds,used);
        return ans;
    }

    public void solve(int[] nums,List<List<Integer>> ans, List<Integer> ds,boolean[] used){
        if(ds.size()==nums.length){
            ans.add(new ArrayList<>(ds));
            return;
        }

        //since each value must be used once in each combination we start from 0 always
        for(int i=0;i<nums.length;i++){
            if(used[i]) continue;
            ds.add(nums[i]); //add to path
            used[i]=true;
            solve(nums,ans,ds,used);
            used[i]=false; //backtrack
            ds.remove(ds.size()-1);
        }
    }
}