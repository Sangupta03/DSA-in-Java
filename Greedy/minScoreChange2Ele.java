package Greedy;
import java.util.*;
class minScoreChange2Ele{
    public int minimizeSum(int[] nums) {
        
        int n=nums.length;
        Arrays.sort(nums);

        //can we just talk about the maxDiff
        //since after sorting minDiff always less than maxDiff

        //so we minimise the range which calculates maxDiff

        //change just the first 2 elements to match nums[2];
        int option1=nums[n-1]-nums[2]; //maxDiff, minDiff=0

        //change the last 2 ele to match nums[n-3];
        int option2=nums[n-3]-nums[0];   //minDiff=0 again

        //change the first ele to match nums[1] and last ele to match nums[n-2]
        int option3=nums[n-2]-nums[1]; //minDiff=0 again

        return Math.min(option1,Math.min(option2,option3));

        //SO MINDIFF IS ALWAYS 0
    }
}