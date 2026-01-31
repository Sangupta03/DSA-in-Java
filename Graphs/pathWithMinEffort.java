package Graphs;
import java.util.*;
class Pair{
    int distance;
    int row;
    int col;
    Pair(int distance,int row,int col){
        this.distance=distance;
        this.row=row;
        this.col=col;
    }
}
class Solution {
    public int minimumEffortPath(int[][] heights) {

        int n=heights.length;
        int m=heights[0].length;

        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->a.distance-b.distance);
        int[][] dist=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                dist[i][j]=(int)1e9;
            }
        }
        dist[0][0]=0;
        int[] drow={-1,0,1,0};
        int[] dcol={0,1,0,-1};
        pq.offer(new Pair(0,0,0));
        while(!pq.isEmpty()){
            Pair curr=pq.poll();
            int diff=curr.distance;
            int row=curr.row;
            int col=curr.col;

            if(row==n-1 && col==m-1) return diff;
            for(int i=0;i<4;i++){
                int nrow=row+drow[i];
                int ncol=col+dcol[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m){
                    int effort=Math.max(Math.abs(heights[row][col]-heights[nrow][ncol]),diff);
                    if(effort<dist[nrow][ncol]){
                        dist[nrow][ncol]=effort;
                        pq.offer(new Pair(effort,nrow,ncol));
                    }
                }
            }
        }
        return 0;
    }
}