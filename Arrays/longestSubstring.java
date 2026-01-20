import java.util.*;
class longestSubstring {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> hp=new HashMap<>(); //<ch, idx>
        int left=0;
        int maxlen=0;
        
        for(int right=0;right<s.length();right++){
            char ch=s.charAt(right);

            if(hp.containsKey(ch)){
                left=Math.max(left,hp.get(ch)+1);
            }

            hp.put(ch,right);
            maxlen=Math.max(maxlen,right-left+1);
        }
        return maxlen;
    }
}