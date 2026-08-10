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


//using dfs

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj=new ArrayList<>();

        int V=numCourses;

        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] e:prerequisites){
            int u=e[1];
            int v=e[0];

            adj.get(u).add(v);
        }
        boolean[] vis=new boolean[V];
        boolean[] pathvis=new boolean[V];

        for(int i=0;i<V;i++){
            if(!vis[i]){
               if(dfs(i,pathvis,vis,adj)){
                return false;
               }
            }
        }
        return true;
    }

    public boolean dfs(int node,boolean[] pathvis,boolean[] vis,List<List<Integer>> adj){
        vis[node]=true;
        pathvis[node]=true;

        for(int nbh:adj.get(node)){
            if(!vis[nbh]){
                if(dfs(nbh,pathvis,vis,adj)){
                    return true;
                }
            }else if(pathvis[nbh]){
                return true;
            }
        }
        pathvis[node]=false;
        return false;
    }
}
