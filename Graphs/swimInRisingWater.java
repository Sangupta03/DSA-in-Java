package Graphs;
import java.util.*;
class Solution {
    public int swimInWater(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        int[][] dist=new int[n][m];
        for(int[] row:dist){
            Arrays.fill(row,(int)1e9);
        }
        dist[0][0]=grid[0][0];
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->a.dis-b.dis);

        pq.offer(new Pair(0,0,grid[0][0]));
        int[] dr={-1,0,1,0};
        int[] dc={0,1,0,-1};

        while(!pq.isEmpty()){
            Pair curr=pq.poll();
            int row=curr.row;
            int col=curr.col;
            int diff=curr.dis;

            if(row==n-1 && col==m-1) return diff;

            for(int i=0;i<4;i++){
                int nrow=dr[i]+row;
                int ncol=dc[i]+col;

                if(nrow>=0 && ncol>=0 && nrow<n && ncol<m){
                    int eff=Math.max(grid[nrow][ncol],diff);
                    if(eff<dist[nrow][ncol]){
                        dist[nrow][ncol]=eff;
                        pq.offer(new Pair(nrow,ncol,eff));
                    }
                }
            }
            
        }
        return -1;

    }
}

class Pair{
    int row;
    int col;
    int dis;
    Pair(int row,int col,int dis){
        this.row=row;
        this.col=col;
        this.dis=dis;
    }
}
