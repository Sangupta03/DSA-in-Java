public class containerwithWaterQ {
    public int maxArea(int[] arr) {

        int n=arr.length;
        int left=0;
        int right=n-1;
        int res=0;

        while(left<right){

            int water=Math.min(arr[left],arr[right])*(right-left);
            res=Math.max(res,water);

            if(arr[right]<arr[left]){
                right--;
            }else{
                left++;
            }
        }
        return res;
    }
} 
  

