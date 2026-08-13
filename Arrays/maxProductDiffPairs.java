class maxProductDiffPairs {
    public int maxProductDifference(int[] nums) {
        int maxi1=0;
        int maxi2=0;

        int low1=Integer.MAX_VALUE;
        int low2=Integer.MAX_VALUE;

        for(int x:nums){
            if(x>maxi1){
                maxi2=maxi1;
                maxi1=x;
            }else if(x>maxi2){
                maxi2=x;
            }
        }
        for(int x:nums){
            if(x<low1){
                low2=low1;
                low1=x;
            }else if(x<low2){
                low2=x;
            }
        }

        return (maxi1*maxi2)-(low1*low2);
    }
}