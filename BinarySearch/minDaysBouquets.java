package BinarySearch;

class minDaysBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        int n=bloomDay.length;
        long val=(long)m*k;

        if(val>bloomDay.length) return -1;

        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            max=Math.max(max,bloomDay[i]);
            min=Math.min(min,bloomDay[i]);
        }

        int low=min;
        int high=max;

        while(low<=high){
            int mid=low+(high-low)/2;

            if(isPossible(bloomDay,m,k,mid)){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return low;
    }

    public boolean isPossible(int[] bloomDay,int m,int k,int minDay){
        int cntBouquet=0;
        int cntFlowers=0;

        for(int i=0;i<bloomDay.length;i++){
            if(bloomDay[i]<=minDay){
                cntFlowers++;
            }else{
                cntBouquet+=cntFlowers/k;   //no. of bouquets that can be made with k continuous flowers
                cntFlowers=0;
            }
        }
        //for the last continuous line of flowers till end
        //calculate bouquets
        cntBouquet+=cntFlowers/k;
        return cntBouquet>=m;
    }
}