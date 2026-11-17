import java.util.*;
class distBtw2Arrays {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        
        Arrays.sort(arr2);
        int cnt=0;

        for(int i=0;i<arr1.length;i++){
            int val=findClosest(arr2,arr1[i]);

            if(Math.abs(arr1[i]-val)>d){
                cnt++;
            }
        }
        return cnt;
    }

    public int findClosest(int[] arr,int val){
        int low=0;
        int high=arr.length-1;
        int closest=arr[0];

        while(low<=high){
            int mid=low+(high-low)/2;

            // Update closest if current mid is nearer to val
            if(Math.abs(arr[mid]-val)<Math.abs(closest-val)){
                closest=arr[mid];
            }

            if(arr[mid]==val){
                return arr[mid];
            }else if(arr[mid]<val){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return closest;
    }
}