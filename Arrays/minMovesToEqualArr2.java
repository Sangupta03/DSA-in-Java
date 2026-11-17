import java.util.*;
class minMovesToEqualArr2 {
    //find the mid value here
    //values less than mid we will incr
    //values more than mid wil desc this will get us the number of moves
    public int minMoves2(int[] nums) {
        int n=nums.length;

        Arrays.sort(nums);
        int mid=nums[n/2];

        int ans=0;

        for(int i=0;i<n/2;i++){
            ans+=Math.abs(mid-nums[i]);
            ans+=Math.abs(mid-nums[n-i-1]);
        }
        return ans;
    }
}