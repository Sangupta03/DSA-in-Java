import java.util.*;
class maxGoodSubarraySum {
    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum=Long.MIN_VALUE;
        long prefix=0;

        // prefix[j]-prefix[i]==k
        //prefix[j]=prefix+num
        //prefix[i]=map.get(num-k)
        HashMap<Integer,Long> map=new HashMap<>();
        for(int num: nums){
            if(map.containsKey(num-k)){
                maxSum=Math.max(maxSum,prefix+num-map.get(num-k));
            }
            if(map.containsKey(num+k)){
                maxSum=Math.max(maxSum,prefix+num-map.get(num+k));
            }

            map.put(num,Math.min(map.getOrDefault(num,Long.MAX_VALUE),prefix));
            prefix+=num;
        }
        return maxSum==Long.MIN_VALUE?0:maxSum;
    }
}