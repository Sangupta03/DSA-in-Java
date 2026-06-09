package BinarySearch;

class countOccurrencesQ {
    public int countOccurrences(int[] arr, int target) {
        int[] ans=new int[2];
        
        ans[0]=findLeftMost(arr,target,true);
        ans[1]=findLeftMost(arr,target,false);
        return ans[1]-ans[0]+1;
    }

    public int findLeftMost(int[] nums,int target,boolean isLeft){

        int n=nums.length;
        int low=0;
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
                    high=mid-1;
                    ans=mid;
                }else{
                    ans=mid;
                    low=mid+1;
                }
            }
        }
        return ans;
    }
}
