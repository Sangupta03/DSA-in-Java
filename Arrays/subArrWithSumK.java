import java.util.*;
//using prefix sum

class subArrWithSumK {
    public int cntSubarrays(int[] arr, int k) {
        
        HashMap<Long,Integer> hp=new HashMap<>();
        hp.put(0L,1);
        long sum=0;
        int cnt=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            long rem=sum-k;
            cnt+=hp.getOrDefault(rem,0);
            hp.put(sum,hp.getOrDefault(sum,0)+1);
        }
        return cnt;
    }
}
