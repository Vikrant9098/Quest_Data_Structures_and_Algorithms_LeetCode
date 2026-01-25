class Solution {                         // Define the Solution class
    public boolean judgeSquareSum(int c) { // Function to check sum of two squares
        for (int divisor = 2; divisor * divisor <= c; divisor++) { // Loop till √c
            if (c % divisor == 0) {        // Check if divisor divides c
                int exponentCount = 0;    // Count power of this divisor
                while (c % divisor == 0) { // Remove all occurrences of divisor
                    exponentCount++;      // Increase power count
                    c /= divisor;         // Divide c by divisor
                }
                if (divisor % 4 == 3 && exponentCount % 2 != 0) { // Invalid case
                    return false;          // Cannot be written as sum of squares
                }
            }
        }
        return c % 4 != 3;                 // Final check for remaining prime
    }
}
