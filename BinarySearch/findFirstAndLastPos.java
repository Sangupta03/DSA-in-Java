package BinarySearch;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        
        int[] ans=new int[2];
        
        ans[0]=findLeftMost(nums,target,true);
        ans[1]=findLeftMost(nums,target,false);
        return ans;
    }

    public int findLeftMost(int[] nums,int target,boolean isLeft){

        int low=0;
        int n=nums.length;
        int high=n-1;
        int ans=-1;

        while(low<=high){
            int mid=low+(high-low)/2;

            if(nums[mid]<target){
                low=mid+1;
            }else if(nums[mid]>target){
                high=mid-1;
            }else{
                if(isLeft){
                    ans=mid;
                    high=mid-1;
                }else{
                    ans=mid;
                    low=mid+1;
                }
            }
        }
        return ans;
    }
}