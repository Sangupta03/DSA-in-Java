package BinarySearch;

class Solution {
    public double minMaxDist(int[] stations, int K) {

        int n = stations.length;

        double low = 0;
        double high = 0;

        // Find the maximum initial gap
        for (int i = 0; i < n - 1; i++) {
            high = Math.max(high, stations[i + 1] - stations[i]);
        }

        double precision = 1e-6;

        while (high - low > precision) {

            double mid = (low + high) / 2.0;

            int requiredStations = countStations(mid, stations);

            if (requiredStations > K) {
                // Need more stations → distance too large
                low = mid;
            } else {
                high = mid;
            }
        }

        return high;
    }

    public int countStations(double maxDist, int[] stations) {

        int count = 0;

        for (int i = 0; i < stations.length - 1; i++) {

            double gap = stations[i + 1] - stations[i];

            int stationsNeeded = (int) (gap / maxDist);

            if (gap == stationsNeeded * maxDist) {
                stationsNeeded--;   // exact division case
            }

            count += stationsNeeded;
        }

        return count;
    }
}
