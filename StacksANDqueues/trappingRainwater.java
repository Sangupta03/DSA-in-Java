package StacksANDqueues;

class trappingRainwater {
    public int trap(int[] height) {
        int n=height.length;
        int l=0;
        int r=n-1;
        int lmax=0;
        int rmax=0;
        int total=0;
        //pge and nge concept optimised
        while(l<r){
            lmax=Math.max(lmax,height[l]);
            rmax=Math.max(rmax,height[r]);

            if(lmax<rmax){
                total+=(lmax-height[l]);
                l++;
            }else{
                total+=(rmax-height[r]);
                r--;
            }
        }
        return total;
    }
}