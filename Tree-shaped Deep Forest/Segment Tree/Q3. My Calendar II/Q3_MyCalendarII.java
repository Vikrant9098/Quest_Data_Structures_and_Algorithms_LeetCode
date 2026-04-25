import java.util.ArrayList;
import java.util.List;

class MyCalendarTwo {

    private List<int[]> bookings;         // list to store all bookings
    private List<int[]> overlapBookings;  // list to store double-booked intervals

    // function to check if two intervals overlap
    private boolean doesOverlap(int start1, int end1, int start2, int end2) {
        return Math.max(start1, start2) < Math.min(end1, end2); // true if overlap exists
    }

    // function to get the overlapping part of two intervals
    private int[] getOverlapped(int start1, int end1, int start2, int end2) {
        return new int[] { Math.max(start1, start2), Math.min(end1, end2) }; // return overlap range
    }

    public MyCalendarTwo() {
        bookings = new ArrayList<>();        // initialize bookings list
        overlapBookings = new ArrayList<>(); // initialize overlap list
    }

    public boolean book(int start, int end) {

        // check against all existing double bookings
        for (int[] booking : overlapBookings) {   // loop through overlap intervals
            if (doesOverlap(booking[0], booking[1], start, end)) { // check overlap
                return false; // triple booking found → reject
            }
        }

        // check overlap with existing single bookings
        for (int[] booking : bookings) { // loop through all bookings
            if (doesOverlap(booking[0], booking[1], start, end)) { // if overlap exists
                overlapBookings.add( // add new overlap interval
                    getOverlapped(booking[0], booking[1], start, end) // compute overlap
                );
            }
        }

        // add current booking to main list
        bookings.add(new int[] { start, end });

        return true; // booking successful
    }
}