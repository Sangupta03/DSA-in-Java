package Sliding_window;
import java.util.*;
class anagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans=new ArrayList<>();

        HashMap<Character,Integer> map=new HashMap<>();
        int n=s.length();
        int m=p.length();
        if(n<m) return ans;
        for(char c:p.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int cnt=map.size(); //no.of characters needed
        int left=0;

        for(int right=0;right<n;right++){
            char r=s.charAt(right);
            if (map.containsKey(r)){
                map.put(s.charAt(right),map.get(s.charAt(right))-1);
                if(map.get(r)==0) cnt--;
            }
            
            if(right-left+1==m){
                if(cnt==0) ans.add(left);
                char l=s.charAt(left);

                if(map.containsKey(l)){
                    if(map.get(l)==0) cnt++;
                    map.put(l,map.getOrDefault(l,0)+1);
                }
                left++;
            }
        }
        return ans;
    }
}