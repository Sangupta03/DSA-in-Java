package Graphs;
import java.util.*;
class Solution {
    public int orangesRotting(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        boolean[][] visited=new boolean[n][m];
        int fresh=0;
        Deque<Pair> q=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==2){
                    q.offer(new Pair(i,j));
                    visited[i][j]=true;
                }else if(grid[i][j]==1){
                    fresh++;
                }
            }
        }
        if(fresh==0){
            return 0;
        }
        int[] dr={0,-1,0,1};
        int[] dc={-1,0,1,0};
        int time=0;

        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                Pair p=q.poll();
                int row=p.row;
                int col=p.col;
                for(int d=0;d<4;d++){
                    int nrow=row+dr[d];
                    int ncol=col+dc[d];

                    if(nrow<0 || ncol<0 || nrow>=m || ncol>=n || grid[nrow][ncol]==2 || grid[nrow][ncol]==0 || visited[nrow][ncol]){
                        break;
                    }else{
                       
                            visited[nrow][ncol]=true;
                            q.offer(new Pair(nrow,ncol));
                            fresh--;
                        
                    }
                }
            }
            time++;
        }
        if(fresh!=0){
            return -1;
        }
        return time;
    }
}
class Pair{
    int row;
    int col;
    Pair(int row,int col){
        this.row=row;
        this.col=col;
    }
}