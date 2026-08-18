package Greedy;
import java.util.*;

class videoStiching {
    public int videoStitching(int[][] clips, int time) {
        int n=clips.length;

        Arrays.sort(clips,(a,b)->a[0]-b[0]);
        int currEnd=0;
        int maxReach=0;
        int jumps=0;
        int i=0;
        while(currEnd<time){
            while(i<n && clips[i][0]<=currEnd){
                maxReach=Math.max(maxReach,clips[i][1]);
                i++;
            }
            if(maxReach==currEnd){
                return -1; //cannot extend more
            }
            currEnd=maxReach;
            jumps++;
        }
        return jumps;
    }
}
