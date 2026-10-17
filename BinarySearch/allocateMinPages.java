package BinarySearch;

class allocateMinPages {
    public int findPages(int[] arr, int k) {
        
        long max=0;
        long sum=0;
        if(arr.length<k) return -1;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            max=Math.max(max,arr[i]);
        }
        long low=max;
        long high=sum;
        
        while(low<=high){
            long mid=low+(high-low)/2;
            
            if(isPossible(arr,k,mid)){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return (int) low;
        
    }
    
    public boolean isPossible(int[] arr, int k, long pagesL){
        int cnt=1;
        long sum=0;
        for(int i=0;i<arr.length;i++){
            if(sum+arr[i]<=pagesL){
                sum+=arr[i];
            }else{
                cnt++;
                sum=arr[i];
            }
        }
        return cnt<=k;
    }
}