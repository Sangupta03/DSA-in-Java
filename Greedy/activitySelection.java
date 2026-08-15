package Greedy;
import java.util.*;
class activitySelection {
    public int activitySelectionQ(int[] start, int[] finish) {
        int n=start.length;
        
        Pair[] items=new Pair[n];
        for(int i=0;i<n;i++){
            items[i]=new Pair(start[i],finish[i]);
        }
        Arrays.sort(items,new comparePair());
        int limit=items[0].finish;
        int cnt=1;
        
        for(int i=1;i<n;i++){
            if(limit<items[i].start){
                cnt++;
                limit=items[i].finish;
            }
        }
        return cnt;
        
    }
}
class comparePair implements Comparator<Pair>{
    @Override
    public int compare(Pair a,Pair b){
        if(a.finish>b.finish){
            return 1;
        }else if(b.finish>a.finish){
            return -1;
        }else{
            return 0;
        }
    }
}
class Pair{
    int start;
    int finish;
    Pair(int start,int finish){
        this.start=start;
        this.finish=finish;
    }
}
