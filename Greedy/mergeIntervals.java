package Greedy;
import java.util.*;
class mergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<int[]> res=new ArrayList<>();
        int n=intervals.length;

        Arrays.sort(intervals,(a,b)->a[0]-b[0]);

        for(int i=0;i<n;i++){
            if(res.isEmpty() || intervals[i][0]>res.get(res.size()-1)[1]){
                res.add(intervals[i]);
            }else{
                int[] last=res.get(res.size()-1);
                last[1]=Math.max(last[1],intervals[i][1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
