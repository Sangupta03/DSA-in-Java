package Greedy;
import java.util.*;
class minPlatforms {
    public int minPlatform(int arr[], int dep[]) {
       
        Arrays.sort(arr);
        Arrays.sort(dep);
        
        int cnt=0;
        int n=arr.length;
        int maxCnt=0;
        int j=0;
        int i=0;
        while(i<n){
            if(arr[i]<=dep[j]){ //arrival, add platform
                cnt++;
                i++;
            }else{ //dept remove platform   dep[j]<=arr[i];
                cnt--;
                j++;
            }
            maxCnt=Math.max(cnt,maxCnt); //max no. of used platform in a time range
        }
        return maxCnt;
    }
}
