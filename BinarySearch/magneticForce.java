package BinarySearch;
import java.util.*;

class magneticForce {
    //similar to aggressive cows
    public int maxDistance(int[] position, int m) {
        int n=position.length;
        Arrays.sort(position); //to find gap/force between balls we need to maximie the min magnetic gap

        int low=1;
        int high=position[n-1]-position[0];

        while(low<=high){
            int mid=low+(high-low)/2;

            if(isSolve(position,m,mid)){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return high;
    }

    public boolean isSolve(int[] position,int m,int force){
        int last=position[0];
        int cnt=1;   //fix one ball at start position

        for(int i=1;i<position.length;i++){
            if(position[i]-last>=force){    //gap valid since we set the min force value
                cnt++;  //fix another ball
                last=position[i];
            }
        }
        return cnt>=m;
    }
}