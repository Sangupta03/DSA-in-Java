package Recursion;
import java.util.*;
class combinations{
    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> ans=new ArrayList<>();
        solve(1,n,k,new ArrayList<>(),ans);
        return ans;
    }

    public void solve(int start,int n,int k,List<Integer> temp,List<List<Integer>> result){

        if(temp.size()==k){
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i=start;i<=n-(k-temp.size())+1;i++){
            temp.add(i);
            solve(i+1,n,k,temp,result);
            temp.remove(temp.size()-1);
        }
    }
}
