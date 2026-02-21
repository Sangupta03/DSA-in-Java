package Heaps;

class convertMinToMaxHeap {
    public int[] minToMaxHeap(int[] nums) {

        int n=nums.length;

        for(int i=(n-1)/2;i>=0;i--){
            heapifyMax(nums,n,i);
        }
        return nums;
    }

    public void heapifyMax(int[] arr, int n, int i){
        int largest=i;
        int left=2*i+1;
        int right=2*i+2;

        if(left<n && arr[left]>arr[largest]){
            largest=left;
        }
        if(right<n && arr[right]>arr[largest]){
            largest=right;
        }
        if(i!=largest){
            int temp=arr[i];
            arr[i]=arr[largest];
            arr[largest]=temp;
            heapifyMax(arr,n,largest);
        }
    }
}