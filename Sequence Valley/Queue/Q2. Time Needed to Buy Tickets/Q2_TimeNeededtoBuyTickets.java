class Solution {                                      // Define the Solution class
    public int timeRequiredToBuy(int[] tickets, int k) { // Method to calculate total time
        
        int total = 0;                                // Store total time taken

        for (int i = 0; i < tickets.length; i++) {    // Loop through each person
            if (i <= k) {                             // If person is before or at k
                total += Math.min(tickets[i], tickets[k]); // Add minimum possible seconds
            } else {                                  // If person is after k
                total += Math.min(tickets[i], tickets[k] - 1); // Add reduced time
            }
        }

        return total;                                 // Return total time needed
    }
}
