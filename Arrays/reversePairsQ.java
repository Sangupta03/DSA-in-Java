class reversePairsQ {
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

    public void merge(int[] nums,int low,int mid,int high){
        
        ArrayList<Integer> temp=new ArrayList<>();
        int left=low;
        int right=mid+1;

        while(left<=mid && right<=high){
            if(nums[left]<nums[right]){
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

        for(int i=0;i<temp.size();i++){
            nums[low+i]=temp.get(i);
        }
    }
    public int cntPairs(int[] nums,int low,int mid,int high){
        int left=low;
        int right=mid+1;
        int cnt=0;

        for(int i=low;i<=mid;i++){
            while(right<=high && nums[i]>2L*nums[right]){
                right++;
            }
            cnt+=(right-(mid+1));
        }
        return cnt;
    }
}