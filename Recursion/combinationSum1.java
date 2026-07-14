package Recursion;
import java.util.*;
class combinationsSum1 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans=new ArrayList<>();
        int n=candidates.length;
        List<Integer> temp=new ArrayList<>();

        solve(0,temp,n,ans,target,candidates);
        return ans;
    }

    public void solve(int idx,List<Integer> arr,int n,List<List<Integer>> ans,int target,int[] nums){

        if(idx==n){
            if(target==0){
                ans.add(new ArrayList<>(arr));
            }
            return;
        }
        if(nums[idx]<=target){//pick
            arr.add(nums[idx]);
            solve(idx,arr,n,ans,target-nums[idx],nums);
            arr.remove(arr.size()-1); //backtrack;

        }

        solve(idx+1,arr,n,ans,target,nums); //notpick
    }
}