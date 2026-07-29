import java.util.*;
//USE PREFIX SUM+ HASHMAP+ MATHS
class subArrDivByK {
    public int subarraysDivByK(int[] nums, int k) {
        int cnt=0;
        HashMap<Long,Integer> hp=new HashMap<>(); //to store remainders
        int n=nums.length;
        hp.put(0L,1);
        long sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            long rem=sum%k;  //IMP
            if(rem<0) rem+=k;
            cnt+=hp.getOrDefault(rem,0);
            hp.put(rem,hp.getOrDefault(rem,0)+1);
        }
        return cnt;
    }
}