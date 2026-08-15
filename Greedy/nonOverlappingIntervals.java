package Greedy;
import java.util.*;

class nonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        int cnt=1;
        int n=intervals.length;

        Arrays.sort(intervals,(a,b)->a[1]-b[1]);
        int limit=intervals[0][1];

        for(int i=1;i<n;i++){
            if(limit<=intervals[i][0]){
                limit=intervals[i][1];
                cnt++;
            }
        }
        return n-cnt;
    }
}
