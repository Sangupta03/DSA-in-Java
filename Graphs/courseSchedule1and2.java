package Graphs;
import java.util.*;
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites){
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();

        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] preq:prerequisites){
            adj.get(preq[1]).add(preq[0]);
        }

        Queue<Integer> q=new LinkedList<>();
        int[] indegree=new int[numCourses];
        for(int i=0;i<numCourses;i++){
            for(int neighbor:adj.get(i)){
                indegree[neighbor]++;
            }
        }
        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0) q.offer(i);
        }

        ArrayList<Integer> order=new ArrayList<>();
        int[] ans=new int[numCourses];
        int cnt=0;

        while(!q.isEmpty()){
            int node=q.poll();
            order.add(node);
            cnt++;
            for(int neighbor:adj.get(node)){
                indegree[neighbor]--;
                if(indegree[neighbor]==0){
                    q.offer(neighbor);
                }
            }
        }
        if(cnt==numCourses){
            for(int i=0;i<numCourses;i++){
                ans[i]=order.get(i);
            }
            return ans;
        }
        else return (new int[]{});

    }
}
