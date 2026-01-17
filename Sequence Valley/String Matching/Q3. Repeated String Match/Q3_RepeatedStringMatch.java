class Solution {                              // Defines the Solution class
    public int repeatedStringMatch(String a, String b) {  // Method to find minimum repeats of 'a'
        StringBuilder gy = new StringBuilder();            // StringBuilder to build repeated string
        int I = 0;                                        // Variable to count repetitions

        for (I = 1; gy.length() <= b.length(); I++) {     // Repeat until gy is longer than b
            gy.append(a);                                 // Add string 'a' to gy
            if (gy.toString().contains(b))                // Check if b is inside gy
                return I;                                 // Return number of repeats
        }

        if (gy.append(a).toString().contains(b))          // Check once more after extra append
            return I;                                     // Return repeat count

        return -1;                                        // Return -1 if b is never found
    }
}
