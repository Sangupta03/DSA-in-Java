import java.util.*;
class topKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        

        HashMap<String,Integer> freq=new HashMap<>();

        for(int i=0;i<words.length;i++){
            freq.put(words[i],freq.getOrDefault(words[i],0)+1);
        }

        PriorityQueue<String> pq=new PriorityQueue<>((a,b)->freq.get(a).equals(freq.get(b))?b.compareTo(a):freq.get(a)-freq.get(b));

        for(String x:freq.keySet()){
            pq.offer(x);
            if(pq.size()>k){
                pq.poll();
            }
        }

        List<String> ans=new ArrayList<>();
        while(!pq.isEmpty()){
            ans.add(pq.poll());
        }
        Collections.reverse(ans);
        return ans;
    }
}