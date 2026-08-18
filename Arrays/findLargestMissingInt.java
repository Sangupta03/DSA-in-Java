import java.util.*;
//divide into three cases
class findLargestMissingInt {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> hp = new HashMap<>();

        for (int x : nums) {
            hp.put(x, hp.getOrDefault(x, 0) + 1);
        }
        //case 1
        if (k == 1) {
            int ans = -1;

            for (Map.Entry<Integer, Integer> entry : hp.entrySet()) {
                if (entry.getValue() == 1) {
                    ans = Math.max(ans, entry.getKey());
                }
            }

            return ans;
        }
        //case 2
        if (k == n) {
            int ans = -1;

            for (int x : nums) {
                ans = Math.max(ans, x);
            }

            return ans;
        }

        //case 3 1<k<n
        //ans will either be first or last value of arr, the one with freq=1 and max value will be the ans
        int first = hp.get(nums[0]) == 1 ? nums[0] : -1;
        int last = hp.get(nums[n - 1]) == 1 ? nums[n - 1] : -1;

        return Math.max(first, last);
    }
}