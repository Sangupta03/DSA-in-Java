class longestIncDecSub {
    public int longestMonotonicSubarray(int[] nums) {
        int ans = 1;
        int highCnt = 1;
        int lowCnt = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                highCnt++;
                lowCnt = 1;
            } else if (nums[i - 1] > nums[i]) {
                lowCnt++;
                highCnt = 1;
            } else {
                highCnt = 1;
                lowCnt = 1;
            }
            ans = Math.max(ans, Math.max(highCnt, lowCnt));
        }

        return ans;
    }
}