package Greedy;

class jumpGame2 {
    public int jump(int[] nums) {
        int jumps=0;
        int maxReach=0;
        int n=nums.length;
        int currEnd=0;
        if(n<=1) return 0;

        for(int i=0;i<n;i++){
            if(i>maxReach){
                break;
            }
            maxReach=Math.max(maxReach,i+nums[i]);

            if(i==currEnd){
                currEnd=maxReach;
                jumps++;
            }

            if(currEnd>=n-1){
                return jumps;
            }
        }
        return jumps;
    }
}