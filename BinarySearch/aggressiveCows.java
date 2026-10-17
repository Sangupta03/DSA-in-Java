package BinarySearch;
import java.util.*;

class aggressiveCows {
    public int aggressiveCowsQ(int[] arr, int k) {
        int n=arr.length;
        Arrays.sort(arr);
        int low=1;
        int high=arr[n-1]-arr[0];  //max gap possible
        
        while(low<=high){
            int mid=low+(high-low)/2;
            
            if(isPossible(arr,k,mid)){
                low=mid+1;  //maximise the val
            }else{
                high=mid-1;
            }
        }
        
        return high; //to return max value
    }
    
    public boolean isPossible(int[] arr,int k, int gap){
        int cnt=1; //no.of cows;
        int prev=arr[0];  //cow placed here
        
        for(int i=1;i<arr.length;i++){
            if(arr[i]-prev>=gap){
                cnt++;
                prev=arr[i];  //place another cow
            }
        }
        return cnt>=k;   //k cows can be placed
    }
}