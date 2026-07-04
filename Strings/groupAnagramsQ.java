import java.util.*;
class groupAnagramsQ {
    public List<List<String>> groupAnagrams(String[] strs) {
    
        HashMap<String,List<String>> hp=new HashMap<>();

        for(String str:strs){
            int[] cnt=new int[26];

            for(char ch:str.toCharArray()){
                cnt[ch-'a']++;
            }

            StringBuilder sb=new StringBuilder();

            for(int i:cnt){
                sb.append(i);
                sb.append('#');
            }

            String key=sb.toString();

            if(!hp.containsKey(key)){
                hp.put(key,new ArrayList<>());
            }

            hp.get(key).add(str);
        }
        return new ArrayList<>(hp.values());
    }
}
