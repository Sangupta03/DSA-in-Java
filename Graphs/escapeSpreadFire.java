package Graphs;
import java.util.*;

class escapeSpreadFire {
    public int maximumMinutes(int[][] grid) {

        int low=0;
        int high=1000000000;
        int ans=-1;
        int[][] fireTime=hazardTime(grid);
        while(low<=high){
            int mid=low+(high-low)/2;
            if(isSafe(grid,fireTime,mid)){
                ans=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return ans;
    }
    public boolean isSafe(int[][] grid,int[][] fireTime,int wait){
        int n=grid.length;
        int m=grid[0].length;

        Deque<int[]> q=new ArrayDeque<>();
        boolean[][] vis=new boolean[n][m];

        q.offer(new int[]{0,0,wait});
        vis[0][0]=true;

        int[] dr={-1,0,1,0};
        int[] dc={0,1,0,-1};
        while(!q.isEmpty()){
            int[] curr=q.poll();
            int row=curr[0];
            int col=curr[1];
            int time=curr[2];
            for(int i=0;i<4;i++){
                int nrow=row+dr[i];
                int ncol=col+dc[i];

                if(nrow<0 || ncol<0 || ncol>=m || nrow>=n || vis[nrow][ncol]){
                    continue;
                }
                int newTime=time+1; //person arrives cell

                if(grid[nrow][ncol]==2) continue; //wall

                if(nrow==n-1 && ncol==m-1){
                    if(fireTime[nrow][ncol]==-1 || newTime<=fireTime[nrow][ncol]){
                        return true;
                    }
                    continue;
                }

                if(newTime>=fireTime[nrow][ncol] && fireTime[nrow][ncol]!=-1){
                    continue; //cell unsafe
                }
                vis[nrow][ncol]=true; //safe // newTime<fireTime
                q.offer(new int[]{nrow,ncol,newTime});
            }
        }
        return false;
    }

    public int[][] hazardTime(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;

        int[][] fireTime=new int[n][m];
        for(int[] row:fireTime){
            Arrays.fill(row,-1);
        }

        Deque<int[]> q=new ArrayDeque<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    fireTime[i][j]=0;
                    q.offer(new int[]{i,j});
                }
            }
        }

        int[] dr={-1,0,1,0};
        int[] dc={0,1,0,-1};

        while(!q.isEmpty()){
            int[] c=q.poll();
            int row=c[0];
            int col=c[1];
            for(int i=0;i<4;i++){
                int nrow=row+dr[i];
                int ncol=col+dc[i];
                if(nrow<0 || ncol<0 || nrow>=n || ncol>=m){
                    continue;
                }
                if(grid[nrow][ncol]==2) continue; //wall
                if(fireTime[nrow][ncol]!=-1) continue;

                fireTime[nrow][ncol]=fireTime[row][col]+1;
                q.offer(new int[]{nrow,ncol});
            }
        }
        return fireTime;
    }
}