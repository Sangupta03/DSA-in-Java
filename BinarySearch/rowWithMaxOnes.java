package BinarySearch;

class rowWithMaxOnes {
    public int rowWithMax1s(int arr[][]) {
        // code here
        int n=arr.length;
        int m=arr[0].length;//col
        int maxCnt=0;
        int maxIdx=-1;
        for(int i=0;i<n;i++){
            int cntOnes=m-findLowerBound(arr[i],1);
            if(cntOnes>maxCnt){
                maxCnt=cntOnes;
                maxIdx=i;
            }
        }
        return maxIdx;
        
    }
    
    public int findLowerBound(int[] arr,int x){
        int n=arr.length;
        int ans=n;
        
        int low=0;
        int high=n-1;
        while(low<=high){
            int mid=(high-low)+low/2;
            if(arr[mid]>=x){
                high=mid-1;
                ans=mid;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }
}