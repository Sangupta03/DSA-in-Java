package Graphs;
import java.util.*;
class wordLadderTwo {
    public Map<String,Integer> mp;
    public String b;
    public List<List<String>> ans;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int sz=beginWord.length();
        mp=new HashMap<>();
        b=beginWord;
        ans=new ArrayList<>();
        Queue<String> q=new LinkedList<>();
        q.offer(beginWord);
        Set<String> st=new HashSet<>();
        for(int i=0;i<wordList.size();i++) st.add(wordList.get(i));
        st.remove(beginWord);
        mp.put(beginWord,1);

        while(!q.isEmpty()){
            String word=q.poll();
            int steps=mp.get(word);

            if(word==endWord) break;
            for(int i=0;i<sz;i++){
                for(char ch='a';ch<='z';ch++){
                    char[] replacedWord=word.toCharArray();
                    replacedWord[i]=ch;
                    String newWord=new String(replacedWord);
                    if(st.contains(newWord)){
                        mp.put(newWord,steps+1);
                        st.remove(newWord);
                        q.offer(newWord);
                    }
                }
            }
        }
        if(mp.containsKey(endWord)){
            ArrayList<String> seq=new ArrayList<>();
            seq.add(endWord);
            dfs(seq,endWord);
        }
        return ans;
    }

    public void dfs(ArrayList<String> seq,String word){

        //base case
        if(word.equals(b)){
            ArrayList<String> dup=new ArrayList<>(seq);
            Collections.reverse(dup);
            ans.add(dup);
            return;
        }
        int steps=mp.get(word);
        int sz=word.length();
        for(int i=0;i<sz;i++){
            for(char ch='a';ch<='z';ch++){
                char[] replacedWord=word.toCharArray();
                replacedWord[i]=ch;
                String nWord=new String(replacedWord);
                if(mp.containsKey(nWord) && (mp.get(nWord)+1==steps)){
                    seq.add(nWord);
                    dfs(seq,nWord);
                    seq.remove(seq.size()-1);
                }
            }
        }
    }
  }
