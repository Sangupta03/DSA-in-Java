package BinarySearch;

class kokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int n=piles.length;

        int low=1;
        int max=0;
        for(int i=0;i<n;i++){
            max=Math.max(piles[i],max);
        }

        int high=max;

        while(low<=high){
            int mid=low+(high-low)/2;

            if(isPossible(piles,h,mid)){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return low;
    }

    public boolean isPossible(int[] piles,int h,int k){

        int cnt=0;
        for(int i=0;i<piles.length;i++){
            cnt+=Math.ceil((double)piles[i]/(double)k);
        }
        return cnt<=h;
    }
}