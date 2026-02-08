package Sliding_window;
import java.util.*;
class permutation_in_string {
    public boolean checkInclusion(String s1, String s2) {

        int n=s2.length();
        if(s2.length()<s1.length()) return false;
        HashMap<Character,Integer> mp=new HashMap<>();
        for(char ch:s1.toCharArray()) mp.put(ch,mp.getOrDefault(ch,0)+1);

        int cnt=mp.size();
        int left=0;
        for(int right=0;right<n;right++){
            char rc=s2.charAt(right);
            if(mp.containsKey(rc)){
                mp.put(rc,mp.getOrDefault(rc,0)-1);
                if(mp.get(rc)==0) cnt--;
            }

            if(right-left+1==s1.length()){
                if(cnt==0){
                    return true;
                }
                char lc=s2.charAt(left);
                if(mp.containsKey(lc)){
                    if(mp.get(lc)==0) cnt++;
                    mp.put(lc,mp.getOrDefault(lc,0)+1);
                }
                left++;
            }

        }
        return false;
    }
}