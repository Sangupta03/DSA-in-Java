import java.util.*;
class minConsecutiveCards {
    public int minimumCardPickup(int[] cards) {
        HashMap<Integer,Integer> hp=new HashMap<>();
        //value, idx
        int len=-1;
        int minLen=Integer.MAX_VALUE;
        for(int i=0;i<cards.length;i++){
            if(hp.containsKey(cards[i])){
                len=i-hp.get(cards[i])+1;
                if(len<minLen){
                    minLen=len;
                }
            }
            hp.put(cards[i],i);
        }
        return minLen==Integer.MAX_VALUE?-1:minLen;
    }
}