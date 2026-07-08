package Greedy;
import java.util.*;
class minimumEffortQ {
    public int minimumEffort(int[][] tasks) {
        
        Arrays.sort(tasks,(a,b)->(b[1]-b[0])-(a[1]-a[0]));
        
        int energy=0;
        int curr=0;

        for(int[] task:tasks){
            if(task[1]<=curr){
                curr-=task[0];
            }else{
                int extra=(task[1]-curr);
                energy+=(task[1]-curr);
                curr+=extra;
                curr-=task[0];
            }
        }
        return energy;
    }
}