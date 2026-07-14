package Recursion;
import java.util.*;
class subsets1 {
    public List<Integer> subsetSums(int[] nums) {
        
        List<Integer> ans=new ArrayList<>();

        solve(0,ans,nums,0);
        return ans;
    }

    public void solve(int idx,List<Integer> ans,int[] nums,int sum){

        if(idx==nums.length){
            ans.add(sum);
            return;
        }

        solve(idx+1,ans,nums,sum+nums[idx]);
        solve(idx+1,ans,nums,sum);
    }
}