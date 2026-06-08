package BinarySearch;

class searchInRotatedArr2 {
    public boolean search(int[] nums, int target) {
        int left=0;
        int n=nums.length;
        int right=n-1;

        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target) return true;
            else if(nums[left]==target && nums[right]==target){
                left++;
                right--;
            }else if(nums[left]<=nums[mid]){
                if(nums[left]<= target && target<=nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if(nums[mid]<=target && target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return false;
    }
}