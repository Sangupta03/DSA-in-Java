package BinarySearch;

class findSqrt {
    public int mySqrt(int x) {
        int res=0;
        int low=1;
        int high=x;

        while(low<=high){
            int mid=low+(high-low)/2;
            long val=(long) mid*mid;
            if(val<=x){
                res=mid;  //potential answer
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return res;
    }
}
