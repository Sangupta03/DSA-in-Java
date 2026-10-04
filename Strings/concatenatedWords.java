import java.util.*;
class concatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        HashSet<String> hs = new HashSet<>();
        HashMap<String, Boolean> marked = new HashMap<>();  // memo

        // O(n) --> n is length of words array
        for (int i = 0; i < words.length; i++) {
            hs.add(words[i]);
        }

        // Check each word to see if it can be formed by concatenating other words in the set
        for (String currentWord : words) {
            if (isConcatenated(currentWord, hs, marked)) {
                res.add(currentWord);
            }
        }
        return res;
    }

    //O(n*(l*l)^4)   due to memo it is O(n*l^3);
    public boolean isConcatenated(String s, HashSet<String> hs,HashMap<String,Boolean> marked){

        if(marked.containsKey(s)){
            return marked.get(s);
        }

        int l=s.length();

        //split each word into prefix and suffix to see if string can be split
        //O(l*l)^2  due to isConcatenated for suffix again + for string is already considered
        for(int i=0;i<l;i++){
            String prefix=s.substring(0,i+1);
            String suffix=s.substring(i+1);

            //case 1 : split into prefix and suffix-2 parts
            //case 2: multiple splits, prefix +suffix split into more parts
            if((hs.contains(prefix) && isConcatenated(suffix,hs,marked)) || (hs.contains(suffix) && hs.contains(prefix))){
                marked.put(s,true);
                //store result , so precomutation can be done reduces TC
                return true;
            }
        }
        marked.put(s,false);
        return false;
    }
}