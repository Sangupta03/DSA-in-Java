package Strings;
import java.util.*;
class permutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int n=s1.length();
        int m=s2.length();

        if(s1==null || s2==null || m<n){
            return false;
        }
        
        int left=0;
        HashMap<Character,Integer> map=new HashMap<>();
        
        for(char ch:s1.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        int cnt=map.size();
        for(int right=0;right<m;right++){
            char r=s2.charAt(right);
            if(map.containsKey(r)){
                map.put(r,map.get(r)-1);
                if(map.get(r)==0) cnt--;
            }

            if(right-left+1==n){
                if(cnt==0) return true;

                char l=s2.charAt(left);
                if(map.containsKey(l)){
                    if(map.get(l)==0) cnt++;
                    map.put(l,map.getOrDefault(l,0)+1);
                }
                left++;
            }
        }
        return false;
    }
}