package Greedy;
import java.util.*;
//almost same concept as jump game 2;
//convert into ranges/intervals then solve
class Solution {
    public int minTaps(int n, int[] ranges) {
        int[][] range=new int[n+1][2];//ranges length is n+1
        for(int i=0;i<=n;i++){
            range[i][0]=i-ranges[i];
            range[i][1]=i+ranges[i];
        }
        Arrays.sort(range,(a,b)->a[0]-b[0]);

        int currEnd=0;
        int taps=0;
        int maxEnd=0;
        int i=0;
        while(currEnd<n){
            while(i<=n && range[i][0]<=currEnd){
                maxEnd=Math.max(maxEnd,range[i][1]);
                i++; //find max coverage
            }
            if(maxEnd==currEnd){
                return -1; //cannot extend range
            }
            //Take the best extension
            currEnd=maxEnd;
            taps++;
        }
        return taps;
    }
}