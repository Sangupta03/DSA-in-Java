package Sliding_window;

class PowerOfKSubarray {
    public int[] resultsArray(int[] nums, int k) {
        int n=nums.length;
        int[] res=new int[n-k+1];
        int cnt=0;
        if(k==1) return nums;
        for(int i=1;i<n;i++){
            if(nums[i]==nums[i-1]+1){
                cnt++;
            }else{
                cnt=0;
            }

            if(i>=k-1){
                if(cnt>=k-1){
                    res[i-k+1]=nums[i];
                }else{
                    res[i-k+1]=-1;
                }
            }
        }
        return res;
    }
}
