package Recursion;
import java.util.*;
class subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();

        List<Integer> temp=new ArrayList<>();
        int n=nums.length;
        Arrays.sort(nums);
        solve(0,temp,ans,n,nums);
        return ans;
    }

    public void solve(int idx,List<Integer> temp,List<List<Integer>> ans,int n,int[] nums){
        
        ans.add(new ArrayList<>(temp));
         
        for(int i=idx;i<n;i++){
            if(i>idx && nums[i]==nums[i-1]) continue;
            temp.add(nums[i]);
            solve(i+1,temp,ans,n,nums);
            temp.remove(temp.size()-1);
        }
    }
}