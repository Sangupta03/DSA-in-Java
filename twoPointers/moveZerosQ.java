package twoPointers;

class moveZeroesQ {
    public void moveZeroes(int[] nums) {
        int j=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                j=i;
                break;
            }
        }
        if(j==-1) return;

        for(int k=j+1;k<nums.length;k++){
            if(nums[k]!=0){
                int temp=nums[k];
                nums[k]=nums[j];
                nums[j]=temp;
                j++;
            }
        }
    }
}
