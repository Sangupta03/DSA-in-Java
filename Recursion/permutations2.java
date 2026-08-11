package Recursion;
import java.util.*;

class permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> ds=new ArrayList<>();
        boolean[] used=new boolean[nums.length];

        Arrays.sort(nums);
        solve(nums,used,ds,ans);
        return ans;
    }

    public void solve(int[] nums,boolean[] used,List<Integer> ds,List<List<Integer>> ans){
        if(ds.size()==nums.length){
            ans.add(new ArrayList<>(ds));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(used[i]) continue;
            if(i>0 && nums[i]==nums[i-1] && !used[i-1]) continue;
            //If this number is the same as the previous number, and the previous number has NOT been used at this level, skip this number.
            used[i]=true;
            ds.add(nums[i]);
            solve(nums,used,ds,ans);
            ds.remove(ds.size()-1);
            used[i]=false;
        }
    }
}