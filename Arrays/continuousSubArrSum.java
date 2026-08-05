import java.util.*;
class continuousSubArrSum {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer,Integer> hp=new HashMap<>();
        hp.put(0,-1);
        //hp.put(0, -1) creates an imaginary prefix sum 0 at index -1, allowing subarrays starting from index 0 to be checked using the same remainder logic.
        int prefix=0;

        
        for(int i=0;i<n;i++){
            prefix+=nums[i];   //find prefixSum
            int rem=prefix%k;  //find rem now
            if(!hp.containsKey(rem)){
                hp.put(rem,i);
            }else{
                int len=i-hp.get(rem);
                if(len>1){
                    return true;
                }
            }
        }
        return false;
    }
}