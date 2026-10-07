package BinarySearch;

class searchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int ans=nums.length;  //number greater than all elements
        int low=0;
        int high=nums.length-1;

        while(low<=high){
            int mid=low+(high-low)/2;

            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                low=mid+1;
            }else{
                ans=mid;
                high=mid-1;
            }
        }
        return ans;

    }
}