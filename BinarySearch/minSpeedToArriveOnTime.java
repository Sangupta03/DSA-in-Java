package BinarySearch;
class minSpeedToArriveOnTime {
    public int minSpeedOnTime(int[] dist, double hour) {
        int low=1;
        int high=10000000;

        while(low<=high){
            int mid=low+(high-low)/2;

            if(isPossible(dist,hour,mid)){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return low>10000000 ? -1: low;
        //is speed greater than maxSpeed return -1;
    }
    public boolean isPossible(int[] dist, double hour, double limit){
        double cnt=0;
        int n=dist.length;
        //limit means speed here
        for(int i=0;i<dist.length-1;i++){
            cnt+=Math.ceil((double)dist[i]/(double)limit);
        }
        cnt+=(double)dist[n-1]/(double)limit;
        //last station no need to wait for full hour
        return cnt<=hour;
    }
}