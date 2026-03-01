import java.util.*;
class findreversePairs {
    public int reversePairs(int[] nums) {
        int n=nums.length;
        return mergeSort(nums,0,n-1);
    }

    public int mergeSort(int[] nums,int low,int high){
        int cnt=0;
        if(low>=high) return cnt;
        int mid=(low+high)/2;
        cnt+=mergeSort(nums,low,mid);
        cnt+=mergeSort(nums,mid+1,high);
        cnt+=cntPairs(nums,low,mid,high);
        merge(nums,low,mid,high);
        return cnt;
    }
    public static void merge(int[] nums,int low,int mid,int high){
        ArrayList<Integer> temp=new ArrayList<>();
        int left=low;
        int right=mid+1;

        while(left<=mid && right<=high){
            if(nums[left]<=nums[right]){
                temp.add(nums[left]);
                left++;
            }else{
                temp.add(nums[right]);
                right++;
            }
        }
        //if ele left in left and right halves
        while(left<=mid){
            temp.add(nums[left++]);
        }
        while(right<=high){
            temp.add(nums[right++]);
        }
        //to transfer from temp to original arr
        for(int i=low;i<=high;i++){
            nums[i]=temp.get(i-low);
        }

    }

    public int cntPairs(int[] nums,int low,int mid,int high){
        int cnt=0;
        int right=mid+1;

        for(int i=low;i<=mid;i++){
            while(right<=high && nums[i]>2L*nums[right]){ //2L to prevent int overflow convert to long
                right++;
            }
            cnt+=(right-(mid+1)); //SINCE IN SECOND ARR ON RIGHT [MID+1(ELE1),ELE2,ELE3(RIGHT POINTER HERE) ,ELE4..,ELE];
            
        }
        return cnt;
    }
}
