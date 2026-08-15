package Greedy;
import java.util.*;

class Solution {
    public int minMeetingRooms(int[] start, int[] end) {
        // Sort start and end times independently
        Arrays.sort(start);
        Arrays.sort(end);
        
        int roomsRequired = 0;
        int endPointer = 0;
        
        // Iterate through all the meetings
        for (int i = 0; i < start.length; i++) {
            // If the current meeting starts before the earliest ending meeting finishes,
            // we have an overlap and need a new room
            if (start[i] < end[endPointer]) {
                roomsRequired++;
            } else {
                // Otherwise, the previous meeting has ended, and we can reuse its room
                // Move the end pointer to the next earliest ending meeting
                endPointer++;
            }
        }
        
        return roomsRequired;
    }
}


