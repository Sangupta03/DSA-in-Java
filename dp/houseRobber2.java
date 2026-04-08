
class houseRobber2 {
    public int rob(int[] nums) {
        int n=nums.length;
        if(n==1) return nums[0];
        int[] n1=new int[n-1];
        int[] n2=new int[n-1];

        
        for(int i=0;i<n-1;i++){
            n1[i]=nums[i];
        }
        for(int i=0;i<n-1;i++){
            n2[i]=nums[i+1];
        }
        return Math.max(solve(n2),solve(n1));
    }

    public int solve(int[] nums){
        int n=nums.length;
    
        int prev=nums[0];
        int prev2=0;

        for(int i=1;i<n;i++){
            int pick=nums[i];
            if(i>1) pick+=prev2;
            int notpick=0+prev;
            int curi=Math.max(pick,notpick);
            prev2=prev;
            prev=curi;
        }
        return prev;
    }
}
