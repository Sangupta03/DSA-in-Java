package Greedy;

class RemoveMinMax {
    public int minimumDeletions(int[] nums) {
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        int minPos=-1;
        int maxPos=-1;

        for(int i=0;i<nums.length;i++){
            if(nums[i]<min){
                min=nums[i];
                minPos=i;
            }if(nums[i]>max){
                max=nums[i];
                maxPos=i;
            }
        }

        int firstIdx=-1;
        int secondIdx=-1;
        if(maxPos<minPos){
            firstIdx=maxPos;
            secondIdx=minPos;
        }else{
            firstIdx=minPos;
            secondIdx=maxPos;
        }
        int n=nums.length;
        int frontR=Math.max(firstIdx,secondIdx)+1;
        int backR=Math.max(n-firstIdx,n-secondIdx);
        int middle=firstIdx+(n-secondIdx)+1;
        return Math.min(frontR,Math.min(backR,middle));
    }
}
