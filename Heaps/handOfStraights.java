package Heaps;

import java.util.*;
class handOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {

        if(hand.length%groupSize!=0) return false;
        if(groupSize==1) return true;
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->a.card-b.card);
        HashMap<Integer,Integer> hp=new HashMap<>();

        for(int x:hand){
            hp.put(x,hp.getOrDefault(x,0)+1);
        }

        for(int key:hp.keySet()){
            pq.offer(new Pair(key,hp.get(key)));
        }

        while(!pq.isEmpty()){
            Pair p=pq.poll();
            p.freq--;

            Queue<Pair> q=new LinkedList<>();
            for(int i=1;i<groupSize;i++){
                if(!pq.isEmpty() && pq.peek().card==p.card+i){
                    Pair curr=pq.poll();
                    curr.freq--;
                    if(curr.freq>0){
                        q.offer(curr);
                    }
                }else{
                    return false;
                }
            }
            while(!q.isEmpty()){
                pq.offer(q.poll());
            }
            if(p.freq>0) pq.offer(p);
        }
        return true;
    }
}

class Pair{
    int card;
    int freq;
    Pair(int card,int freq){
        this.card=card;
        this.freq=freq;
    }
}