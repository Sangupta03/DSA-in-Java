package Heaps;
import java.util.*;
class Pair{
    int i;
    int j;
    int sum;
    Pair(int i,int j,int sum){
        this.i=i;
        this.j=j;
        this.sum=sum;
    }
}
class maxSumCombination {
    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        // code here
        PriorityQueue<Pair> maxHeap=new PriorityQueue<>((x,y)->y.sum-x.sum);
        ArrayList<Integer> res=new ArrayList<>();
        int n=a.length;
        HashSet<String> visited=new HashSet<>();
        Arrays.sort(a);
        Arrays.sort(b);
        
        maxHeap.offer(new Pair(n-1,n-1,a[n-1]+b[n-1]));
        visited.add((n-1)+","+(n-1));
        
        while(k-->0 && !maxHeap.isEmpty()){
            Pair p=maxHeap.poll();
            int sum=p.sum;
            res.add(sum);
            
            int i=p.i;
            int j=p.j;
            if(i-1>=0){
                String key1=(i-1)+","+j;
                if(!visited.contains(key1)){
                    maxHeap.offer(new Pair(i-1,j,a[i-1]+b[j]));
                    visited.add(key1);
                }
                
            }
            if(j-1>=0){
                String key2= i+ ","+(j-1);
                if(!visited.contains(key2)){
                    maxHeap.offer(new Pair(i,j-1,a[i]+b[j-1]));
                    visited.add(key2);  
                }
                
            }
        }
        return res;
        
    }
}