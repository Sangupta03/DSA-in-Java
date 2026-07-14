package Recursion;
import java.util.*;
class combinationSum3 {
    public List<List<Integer>> combinationSum3Q(int k, int n) {
        List<List<Integer>> ans=new ArrayList<>();

        List<Integer> temp=new ArrayList<>();
        solve(1,n,k,temp,ans);
        return ans;
    }

    public void solve(int idx,int n,int k,List<Integer> temp,List<List<Integer>> ans){

        if(n==0 && k==0){
            ans.add(new ArrayList<>(temp));
            return;
        }

        if(k<0 || n<0) return;
        for(int i=idx;i<=9;i++){
            temp.add(i);
            solve(i+1,n-i,k-1,temp,ans);
            temp.remove(temp.size()-1);
        }
    }
}