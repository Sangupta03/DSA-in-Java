package Graphs;
import java.util.*;
class reconstructItinerary {
    List<String> ans=new ArrayList<>();
    HashMap<String,PriorityQueue<String>> hp=new HashMap<>(); //adj list to map lexicographically naturally

    public List<String> findItinerary(List<List<String>> tickets) {
        
        for(List<String> str:tickets){
            String from=str.get(0);
            String to=str.get(1);

            if(!hp.containsKey(from)){
                hp.put(from,new PriorityQueue<>());
            }
            hp.get(from).offer(to);
        }

        dfs("JFK");

        Collections.reverse(ans); //reverse ans since dfs adds ans while bactracking to source/ postorder travrsal //from dest to source it adds so we reverse it
        return ans;
    }

    public void dfs(String airport){

        PriorityQueue<String> pq=hp.get(airport);  //give from-->dest(multiple airports lexicographically)

        while(pq!=null && !pq.isEmpty()){
            String next=pq.poll();
            dfs(next);  //try postorder traversal and map the source to dest till end
        }

        ans.add(airport); //after dfs complete then only adds the relevant node
    }
}