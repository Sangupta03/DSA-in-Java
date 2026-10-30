package Sliding_window;
import java.util.*;

class minPositiveSum {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        
        int n=nums.size();
        int minSum=Integer.MAX_VALUE;

        for(int size=l;size<=r;size++){
            int currSum=0;
            for(int i=0;i<size;i++){
                currSum+=nums.get(i);
            }
            if(currSum>0){
                minSum=Math.min(minSum,currSum);
            }

            for(int i=size;i<n;i++){
                currSum+=nums.get(i);
                currSum-=nums.get(i-size);
                if(currSum>0){
                    minSum=Math.min(minSum,currSum);
                }
            }

        }
        return minSum==Integer.MAX_VALUE?-1:minSum;
    }
}