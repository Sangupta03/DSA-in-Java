package Graphs;

class jumpGame3 {
    public boolean canReach(int[] arr, int start) {
        int n=arr.length;
        boolean[] visited=new boolean[n];

        return dfs(start,visited,arr);
    }

    public boolean dfs(int x,boolean[] visited,int[] arr){
        
        if(x<0 || x>=arr.length) return false;
        if(visited[x]) return false;  //to prevent infinite loop
        if(arr[x]==0) return true;

        visited[x]=true;

        return dfs(x+arr[x],visited,arr) || dfs(x-arr[x],visited,arr);
    }
}