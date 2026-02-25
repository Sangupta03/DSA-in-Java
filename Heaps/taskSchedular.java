package Heaps;
import java.util.*;
class taskSchedular {
    public int leastInterval(char[] tasks, int n) {

        int time=0;
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->b.count-a.count);
        //maxheap
        Queue<Pair> q=new LinkedList<>();
        int[] freq=new int[26];

        for(char ch:tasks){
            freq[ch-'A']++;
        }

        for(int i=0;i<26;i++){
            if(freq[i]>0){
                pq.offer(new Pair(0,freq[i]));
            }
        }

        while(!pq.isEmpty() || !q.isEmpty()){
            if(!q.isEmpty() && time-q.peek().time>n){
                pq.offer(q.poll());
            }
            if(!pq.isEmpty()){
                Pair p=pq.poll();
                p.count--;
                if(p.count>0) q.offer(new Pair(time,p.count));
            }
            time++;
        }
        return time;
    }
}
class Pair{
    int time;
    int count;
    Pair(int time,int count){
        this.time=time;
        this.count=count;
    }
}
