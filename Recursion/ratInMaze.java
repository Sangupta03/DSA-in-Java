package Recursion;
import java.util.*;

class ratInMaze {
    public ArrayList<String> ratInMaze(int[][] maze) {
        // code here
        ArrayList<String> ans=new ArrayList<>();
        int n=maze.length;
        int m=maze[0].length;
        boolean[][] vis=new boolean[n][m];
        solve(0,0,maze,vis,ans,new StringBuilder());
        return ans;
    }
    
    public void solve(int i,int j,int[][] maze,boolean[][] vis,ArrayList<String> ans,StringBuilder path){
        int n=maze.length;
        int m=maze[0].length;
        
        if(i==n-1 && j==m-1){
            ans.add(path.toString());
            return;
        }
        if(i>=n || j>=m || i<0 || j<0 || vis[i][j] || maze[i][j]==0){
            return;
        }
        
        char[] dir={'D','L','R','U'};
        int[] dr={1,0,0,-1};
        int[] dc={0,-1,1,0};
        
        vis[i][j]=true;
        
        for(int idx=0;idx<4;idx++){
            path.append(dir[idx]);
            solve(i+dr[idx],j+dc[idx],maze,vis,ans,path);
            path.deleteCharAt(path.length()-1);
        }
        
        vis[i][j]=false;
        
    }    
}
