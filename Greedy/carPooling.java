package Greedy;

import java.util.*;
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        // Sort the trips based on their start location
        Arrays.sort(trips, (a, b) -> Integer.compare(a[1], b[1]));
        
        // Min-Heap to store currently active trips, sorted by their end location
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        
        int currentPassengers = 0;
        
        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int startLocation = trip[1];
            
            // Drop off passengers whose trips have ended before or at the current start location
            while (!pq.isEmpty() && pq.peek()[2] <= startLocation) {
                currentPassengers -= pq.poll()[0];
            }
            
            // Pick up new passengers and add the current trip to active trips
            currentPassengers += numPassengers;
            pq.offer(trip);
            
            // Check if exceeded the car's capacity
            if (currentPassengers > capacity) {
                return false; //since have to accomodate all trips
            }
        }
        
        return true;
    }
}