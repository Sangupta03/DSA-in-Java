package twoPointers;

class sortArrayByParityQ {
    public int[] sortArrayByParity(int[] nums) {

        int[] res = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            res[i] = nums[i];
        }

        int evenIndex = 0;

        for(int i = 0; i < nums.length; i++){
            if(res[i] % 2 == 0){
                int temp = res[evenIndex];
                res[evenIndex] = res[i];
                res[i] = temp;
                evenIndex++;
            }
        }
        return res;
    }
}
