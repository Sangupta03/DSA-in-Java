package Recursion;
import java.util.*;
class palindromePart {
    public List<List<String>> partition(String s) {
        //your code goes here
        List<List<String>> ans=new ArrayList<>();
        List<String> res=new ArrayList<>();

        solve(0,s,ans,res);
        return ans;
    }

    public void solve(int idx,String s,List<List<String>> ans,List<String> temp){

        if(idx==s.length()){
            ans.add(new ArrayList<>(temp));
            return;
        }

        for(int i=idx;i<s.length();i++){

            if(isPalindrome(idx,i,s)){
                temp.add(s.substring(idx,i+1));
                solve(i+1,s,ans,temp);
                temp.remove(temp.size()-1);
            }
        }
    }

    public boolean isPalindrome(int start,int end,String s){
        
        while(start<=end){
            if(s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}