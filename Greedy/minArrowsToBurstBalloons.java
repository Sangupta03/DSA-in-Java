package Greedy;
import java.util.*;
class minArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {

        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        int n=points.length;
        int cnt=1;

        int limit=points[0][1];
        for(int i=1;i<n;i++){
            if(limit<points[i][0]){
                cnt++;
                limit=points[i][1];
            }
        }
        return cnt;
    }
}