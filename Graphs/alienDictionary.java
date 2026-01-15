package Graphs;
import java.util.*;

class Solution {
    public String findOrder(String[] words) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        int k=26;
        
        for(int i=0;i<k;i++){
            adj.add(new ArrayList<>());
        }
        
        boolean[] present=new boolean[k];
        int[] indegree=new int[k];
        
        for(String word:words){
            for(char ch:word.toCharArray()){
                present[ch-'a']=true;
            }
        }
        
        for(int i=0;i<words.length-1;i++){
            String s1=words[i];
            String s2=words[i+1];
            
            //invalid case
            if(s1.length()>s2.length() && s1.startsWith(s2)){
                return "";
            }
            
            int len=Math.min(s1.length(),s2.length());
            
            for(int ptr=0;ptr<len;ptr++){
                if(s1.charAt(ptr)!=s2.charAt(ptr)){
                    adj.get(s1.charAt(ptr)-'a').add(s2.charAt(ptr)-'a');
                }
            }
            
        }
        String topo=topoSort(k,adj,present,indegree);
        
        return topo;
    }
    public String topoSort(int k,ArrayList<ArrayList<Integer>> adj,boolean[] present,int[] indegree){
        
        int charcnt=0;
        for(int i=0;i<k;i++){
            if(present[i]){
                charcnt++;
                for(int neigbor:adj.get(i)){
                    indegree[neigbor]++;
                }
            }
        }
        Queue<Integer> q=new LinkedList<>();
        
        for(int i=0;i<k;i++){
            if(indegree[i]==0 && present[i]){
                q.offer(i);
            }
        }
        StringBuilder ans=new StringBuilder();
       
        
        //topoSort
        while(!q.isEmpty()){
            int node=q.poll();
            ans.append((char)node+'a');
            
            for(int neigbor:adj.get(node)){
                indegree[neigbor]--;
                if(indegree[neigbor]==0 && present[neigbor]){
                    q.offer(neigbor);
                }
            }
        }
        if(ans.length()==charcnt){
            return ans.toString();
        }else{
            return "";
        }
        
    }
}