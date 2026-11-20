package BinarySearch;
import java.util.*;

class cntElements2Arrays {
    public static ArrayList<Integer> countLessEq(int a[], int b[]) {
        // code here
        Arrays.sort(b);
        
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            ans.add(upperBound(a[i], b));
        }

        return ans;
    }
    
    
    public static int upperBound(int x,int[] arr){
        int low=0;
        int high=arr.length-1;
        
        int ans=-1;
        
        while(low<=high){
            int mid=low+(high-low)/2;
            
            if(arr[mid]<=x){
                low=mid+1;
                ans=mid;
            }else{
                high=mid-1;
            }
        }
        return ans+1;
        
    }
}