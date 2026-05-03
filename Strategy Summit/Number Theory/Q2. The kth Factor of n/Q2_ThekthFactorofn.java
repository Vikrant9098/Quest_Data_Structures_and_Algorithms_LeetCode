class Solution {
    public int kthFactor(int n, int k) {
        // Counter to track how many factors of n we have found so far
        int count = 0;

        // Loop from 1 to n (inclusive) to check all possible factors
        for (int i = 1; i <= n; i++) {
            
            // Check if i divides n completely (i is a factor of n)
            if (n % i == 0) {
                
                // Increment the count of factors
                count++;
                
                // If this is the k-th factor, return it immediately
                if (count == k)
                    return i;
            }
        }

        // If n has fewer than k factors, return -1
        return -1;
    }
}