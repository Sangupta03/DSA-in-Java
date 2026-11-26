import java.util.*;
class removeCoveredIntervals {
    public int removeCoveredIntervalsQ(int[][] intervals) {
        int cnt=0;
        Arrays.sort(intervals,(a,b)->{
            if(a[0]!=b[0]) return Integer.compare(a[0],b[0]);
            else return Integer.compare(b[1],a[1]);
        });

        int minStart=Integer.MAX_VALUE;
        int maxEnd=Integer.MIN_VALUE;
        for(int[] interval:intervals){
            if(minStart!=Integer.MAX_VALUE && interval[0]>=minStart){
                minStart=Math.min(interval[0],minStart);

                if(maxEnd!=Integer.MIN_VALUE && interval[1]<=maxEnd){
                    maxEnd=Math.max(interval[1],maxEnd);
                    cnt++; //since both start end are contained
                }else{
                    maxEnd=interval[1];
                }
            }else{
                minStart=Math.min(minStart,interval[0]);
                maxEnd=Math.max(maxEnd,interval[1]);
            }
            
        }
        return intervals.length-cnt;
    }
}

//simplest modification

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int cnt = 0;
        // Sort start ascending; if starts are equal, sort end descending
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            else return Integer.compare(b[1], a[1]);
        });

        int maxEnd = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[1] <= maxEnd) {
                cnt++; // Covered by a previous interval
            } else {
                maxEnd = interval[1]; // Extend the furthest right boundary seen so far
            }
        }
        return intervals.length - cnt;
    }
}