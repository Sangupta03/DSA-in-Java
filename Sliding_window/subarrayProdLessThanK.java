package Sliding_window;

class numSubarrayProdLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int currProd=1;
        int maxCnt=0;

        int left=0;
        if(k <= 1) return 0;
        for(int right=0;right<nums.length;right++){
            currProd*=nums[right];

            while(currProd>=k){
                currProd/=nums[left];
                left++;
            }
            maxCnt+=(right-left+1);
        }
        return maxCnt;
    }
}