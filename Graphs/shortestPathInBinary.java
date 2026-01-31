package Graphs;
import java.util.*;
class Tuple{
    int distance,row,col;
    Tuple(int distance,int row,int col){
        this.distance=distance;
        this.row=row;
        this.col=col;
    }
}
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n=grid.length;

        int[][] dist=new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dist[i][j]=(int) 1e9;
            }
        }
        if(grid[0][0]==1) return -1;
        if(n==1) return 1;
        dist[0][0]=1;
        Queue<Tuple> q=new LinkedList<>();
        q.offer(new Tuple(1,0,0));

        while(!q.isEmpty()){
            Tuple curr=q.poll();
            int r=curr.row;
            int c=curr.col;
            int distance=curr.distance;

            for(int drow=-1;drow<=1;drow++){
                for(int dcol=-1;dcol<=1;dcol++){
                    if(drow==0 && dcol==0) continue;
                    int nrow=r+drow;
                    int ncol=c+dcol;
                    if(nrow>=0 && nrow<n && ncol>=0 && ncol<n && grid[nrow][ncol]==0 && 1+distance<dist[nrow][ncol]){
                        dist[nrow][ncol]=1+distance;
                        if(nrow==n-1 && ncol==n-1) return distance+1;
                        q.offer(new Tuple(1+distance,nrow,ncol));
                    }
                }
            }
        }
        return -1;
    }
}
