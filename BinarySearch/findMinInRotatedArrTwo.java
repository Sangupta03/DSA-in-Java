class findMinInRotatedArrTwo {
    public int findMin(int[] nums) {
        int n=nums.length;
        int low=0;
        int high=n-1;

        int minVal=Integer.MAX_VALUE;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[low]<nums[high]){
                if(nums[low]<=minVal){
                    minVal=Math.min(minVal,nums[low]);
                }
                break;
            }
            if(nums[low]==nums[mid] && nums[mid]==nums[high]){
                minVal=Math.min(minVal,nums[mid]);
                low++;
                high--;
            }
            else if(nums[low]<=nums[mid]){
                
                minVal=Math.min(minVal,nums[low]);
                low=mid+1;
            }else{
                minVal=Math.min(minVal,nums[mid]);
                high=mid-1;
            }
            
        } 
        return minVal;                  
    }
}