class minMovesToEqualArr {

    //try to find the gap between min and max ele each time
    //the total sum of gaps will be the ans
    //instead of inc n-1 ele each time, we can decrese maxEle in arr each time by 1
    //will result in the same ans;

    //[1,2,3] -> [1,2,2] -> [1,2,1] -> [1,1,1]
    //so three moves
    //ans=ele-minEle
    //observe ans= (2-1)+(3-1)=3

    //we do for all the elements ele-minEle
    //so  sum of n ele - n*minEle will give same ans

    public int minMoves(int[] nums) {
        int n=nums.length;

        long sum=0;

        int minEle=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            sum+=nums[i];
            minEle=Math.min(minEle,nums[i]);
        }

        return (int)sum-n*minEle;
    }
}