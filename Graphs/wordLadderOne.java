package Graphs;

class Pair{
    String word;
    int len;
    Pair(String word,int len){
        this.word=word;
        this.len=len;
    }
}
class wordLadderOne {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len=wordList.size();
        Set<String> st=new HashSet<>();

        for(int i=0;i<len;i++){
            st.add(wordList.get(i));
        }
        Queue<Pair> q=new LinkedList<>();
        q.offer(new Pair(beginWord,1));
        st.remove(beginWord);

        while(!q.isEmpty()){
            String word=q.peek().word;
            int steps=q.peek().len;
            q.poll();
            if(word.equals(endWord)) return steps;
            int wordlen=word.length();
            for(int i=0;i<wordlen;i++){
                for(char ch='a';ch<='z';ch++){
                    char[] wordReplaced=word.toCharArray();
                    wordReplaced[i]=ch;
                    String newWord=new String(wordReplaced);
                    if(st.contains(newWord)){
                        q.offer(new Pair(newWord,steps+1));
                        st.remove(newWord);
                    }
                }
            }
        }
        return 0;
    }
}