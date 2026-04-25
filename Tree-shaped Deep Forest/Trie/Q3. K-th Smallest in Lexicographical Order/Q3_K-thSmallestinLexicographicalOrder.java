class Solution {

    public int findKthNumber(int n, int k) {
        int curr = 1;          // Start from the smallest lexicographical number
        k--;                   // We already consider '1' as the first number

        // Loop until we reach the k-th number
        while (k > 0) {

            // Count how many numbers exist between current prefix (curr)
            // and the next prefix (curr + 1)
            int step = countSteps(n, curr, curr + 1);

            // If total numbers under this prefix are less than or equal to k
            if (step <= k) {
                // Skip this entire subtree (all numbers starting with curr)
                
                curr++;        // Move to the next prefix (next sibling)
                k -= step;     // Reduce k by how many numbers we skipped

            } else {
                // Otherwise, the k-th number lies inside this subtree
                
                curr *= 10;    // Go deeper into the tree (first child)
                k--;           // We move one step down, so decrease k by 1
            }
        }

        return curr;           // Return the k-th lexicographical number
    }

    // Helper function to count how many numbers exist between prefix1 and prefix2
    private int countSteps(int n, long prefix1, long prefix2) {
        int steps = 0;         // Initialize step counter

        // Continue while prefix1 is within the limit n
        while (prefix1 <= n) {

            // Count numbers between prefix1 and prefix2
            // (bounded by n + 1 to avoid overflow)
            steps += Math.min(n + 1, prefix2) - prefix1;

            // Move to the next level in the tree (add one more digit)
            prefix1 *= 10;
            prefix2 *= 10;
        }

        return steps;          // Return total count of numbers in this range
    }
}