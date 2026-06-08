import java.util.*;
class inversionCnt {
    static int inversionCount(int arr[]) {
        int n=arr.length;
        // Code Here
        return mergeSort(arr,0,n-1);
    }
    
    static int mergeSort(int[] arr,int low,int high){
        
        int cnt=0;
        if(low>=high) return cnt;
        int mid=(low+high)/2;
        
        cnt+=mergeSort(arr,low,mid);
        cnt+=mergeSort(arr,mid+1,high);
        cnt+=merge(arr,low,mid,high);
        return cnt;
    }
    
    static int merge(int[] arr,int low,int mid,int high){
        
        ArrayList<Integer> temp=new ArrayList<>();
        int cnt=0;
        
        int left=low;
        int right=mid+1;
        
        while(left<=mid && right<=high){
            if(arr[left]>arr[right]){
                temp.add(arr[right]);
                cnt+=(mid-left+1);
                right++;
                
            }else{
                temp.add(arr[left]);
                left++;
            }
        }
        while(left<=mid){
            temp.add(arr[left]);
            left++;
        }
        while(right<=high){
            temp.add(arr[right]);
            right++;
        }
        
        for(int i=0;i<temp.size();i++){
            arr[i+low]=temp.get(i);
        }
        return cnt;
        
    }
}