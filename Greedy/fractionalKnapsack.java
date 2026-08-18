package Greedy;
import java.util.*;

class fractionalKnapsack {
    public double fractionalKnapsackQ(int[] val, int[] wt, int capacity) {
        int n=val.length;
        Pair[] items=new Pair[n];
        for(int i=0;i<n;i++){
            items[i]=new Pair(val[i],wt[i]);
        }
        Arrays.sort(items,new comparePair());
        double ans=0;
        for(Pair x:items){
            if(x.weight<=capacity){
                ans+=x.value;
                capacity-=x.weight;
            }else{
                ans+=((double)x.value/(double)x.weight)*capacity;
                break; //get out of loop
            }
        }
        return ans;
    }
}
class Pair{
    int value;
    int weight;
    Pair(int value,int weight){
        this.value=value;
        this.weight=weight;
    }
}
//sort descending order
class comparePair implements Comparator<Pair>{
    public int compare(Pair a,Pair b){
        if((double)a.value/(double)a.weight < (double)b.value/(double)b.weight){
            return 1;
        }else if((double)a.value/(double)a.weight > (double)b.value/(double)b.weight){
            return -1;
        }else{
            return 0;
        }
    }
}
