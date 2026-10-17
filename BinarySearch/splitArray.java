package BinarySearch;

class splitArray {
    public int splitArrayQ(int[] nums, int k) {
        long sum=0;
        long max=0;

        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            max=Math.max(max,nums[i]);
        }

        long low=max;
        long high=sum;

        while(low<=high){
            long mid=low+(high-low)/2;

            if(isPossible(nums,k,mid)){
                high=mid-1;  //try to minimize the value, to get more k splits
            }else{
                low=mid+1;
            }
        }
        return (int) low;

    }
    public boolean isPossible(int[] nums,int k,long pageLimit){
        int cnt=1;
        long sum=0;

        for(int i=0;i<nums.length;i++){
            if(sum+nums[i]<=pageLimit){
                sum+=nums[i];
            }else{
                cnt++;
                sum=nums[i];
            }
        }
        return cnt<=k;
    }
}