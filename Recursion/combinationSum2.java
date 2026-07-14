package Recursion;
import java.util.*;
class combinationSum2 {
    public List<List<Integer>> combinationSum2Q(int[] candidates, int target) {
        List<List<Integer>> ans=new ArrayList<>();

        List<Integer> temp=new ArrayList<>();
        Arrays.sort(candidates);
        int n=candidates.length;
        solve(0,target,temp,ans,n,candidates);
        return ans;
    }

    public void solve(int idx,int target,List<Integer> temp,List<List<Integer>> ans,int n,int[] nums){
        
        if(target==0){
            ans.add(new ArrayList<>(temp));
            return;
        }

        for(int i=idx;i<n;i++){
            if(i>idx && nums[i]==nums[i-1]) continue;
            if(nums[i]>target) break;
            temp.add(nums[i]);
            solve(i+1,target-nums[i],temp,ans,n,nums);
            temp.remove(temp.size()-1);
        }
    }
}