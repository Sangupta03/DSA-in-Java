package BinarySearch;

class houseRobber4 {
    public int minCapability(int[] nums, int k) {
       
        int min=Integer.MAX_VALUE;
        int max=0;

        for(int x:nums){
            if(x<min){
                min=x;
            }
            if(x>max){
                max=x;
            }
        }
        int low=min;
        int high=max;
      
        while(low<=high){
            int mid=(low+high)/2;

            if(check(mid,nums,k)){  //maximum capability of robber check
                high=mid-1;
            }else{
                low=mid+1;
            }
        } 
        return low;
    }

    public boolean check(int val,int[] nums,int k){
        int cnt=0;
        int idx=0;

        while(idx<nums.length){
            if(nums[idx]<=val){
                idx+=2;
                cnt++;
            }else{
                idx++;
            }
        }
        return cnt>=k;
    }
}