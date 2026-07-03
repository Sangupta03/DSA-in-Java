
package Sliding_window;

import java.util.*;
class groupAnagramsQ {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> res = new HashMap<>();

        for(String str: strs){
            int[] count=new int[26];
            for(char ch: str.toCharArray()){
                count[ch-'a']++;
            }
            StringBuilder sb=new StringBuilder();
            for (int i:count){
                sb.append(i);
                sb.append('#');
            }
            String key=sb.toString();
            if(!res.containsKey(key)){
                res.put(key,new ArrayList<String>());
            }
            res.get(key).add(str);
        }
        return new ArrayList<>(res.values());
    }
}