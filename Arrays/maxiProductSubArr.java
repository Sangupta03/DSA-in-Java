class maxiProductSubArr {
    public int maxProduct(int[] nums) {
        int currMin=1;
        int currMax=1;

        int maxiProd=Integer.MIN_VALUE;
        int n=nums.length;

        for(int i=0;i<n;i++){
            if(nums[i]>0){
                currMin=Math.min(nums[i],currMin*nums[i]);
                currMax=Math.max(nums[i],currMax*nums[i]);
            }else if(nums[i]==0){
                currMin=0;
                currMax=0;
            }else{
                int temp=currMax;
                currMax=Math.max(nums[i],nums[i]*currMin);
                currMin=Math.min(nums[i],nums[i]*temp);
            }
            maxiProd=Math.max(maxiProd,currMax);
        }
        return maxiProd;
    }
}