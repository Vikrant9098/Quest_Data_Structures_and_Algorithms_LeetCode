class Solution {                                     // Defines the Solution class
    public boolean repeatedSubstringPattern(String s) { // Method to check repeated pattern
        int n = s.length();                           // Store length of the string

        for (int i = 1; i <= n / 2; i++) {            // Try all possible substring lengths
            if (n % i == 0 &&                         // Check if i divides total length
                s.substring(0, i)                     // Take prefix of length i
                 .repeat(n / i)                       // Repeat it to match full length
                 .equals(s)) {                        // Compare with original string
                return true;                          // Pattern found
            }
        }

        return false;                                 // No repeating pattern exists
    }
}
