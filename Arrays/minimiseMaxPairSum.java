import java.util.*;
class minimiseMaxPair {
    public int minPairSum(int[] nums) {
        int maxi=0;

        Arrays.sort(nums);
        int low=0;
        int n=nums.length;
        int high=n-1;

        while(low<high){
            int sum=nums[low]+nums[high];
            maxi=Math.max(maxi,sum);
            low++;
            high--;
        }
        return maxi;
    }
}