package Heaps;
import java.util.*;
class replaceEleByRank {
    static int[] replaceWithRank(int arr[], int N) {
        // code here
        int rank=1;
        HashMap<Integer,Integer> hp=new HashMap<>();
        
        int[] sorted=arr.clone();
        Arrays.sort(sorted);
        
        for(int num:sorted){
            if(!hp.containsKey(num)){
                hp.put(num,rank);
                rank++;
            }
        }
        
        for(int i=0;i<arr.length;i++){
            arr[i]=hp.get(arr[i]);
        }
        return arr;
    }
}