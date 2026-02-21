package Heaps;
class checkMinHeap {
    public boolean isHeap(int[] nums) {
        int n=nums.length;

        if(n==1) return true;
        for(int i=0;i<=(n/2)-1;i++){
            int left=2*i+1;
            int right=2*i+2;

            if(left<n && nums[left]<nums[i]){
                return false;
            }
            if(right<n && nums[right]<nums[i]){
                return false;
            }
        }
        return true;

    }
}