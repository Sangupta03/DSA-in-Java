package Greedy;
import java.util.*;
class maxBagswithFullCap {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {

        int n = capacity.length;
        int[] need = new int[n];

        for (int i = 0; i < n; i++) {
            need[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(need);

        int cnt = 0;

        for (int num : need) {
            if (num == 0) {
                cnt++;
            } else if (additionalRocks >= num) {
                additionalRocks -= num;
                cnt++;
            } else {
                break;
            }
        }

        return cnt;
    }
}