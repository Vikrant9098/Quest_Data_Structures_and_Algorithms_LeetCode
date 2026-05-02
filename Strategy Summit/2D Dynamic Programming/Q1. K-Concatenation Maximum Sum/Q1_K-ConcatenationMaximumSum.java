class Solution {

    // Stores total sum of one full array (used later in calculation)
    long total;

    public int kConcatenationMaxSum(int[] arr, int k) {

        // Length of original array
        int len = arr.length;

        // Modulo value as per problem constraint
        int MOD = 1_000_000_007;

        // If only one concatenation, directly apply Kadane
        if (k == 1) {
            long ans = finderMax(arr, len, len);
            return (int) (ans % MOD);
        }

        // Find max subarray sum for 2 concatenations (important case)
        long ans1 = finderMax(arr, len * 2, len);

        // This will store result when total sum is positive
        long ans2 = 0;

        // If total sum of array is positive, more concatenations help
        if (total > 0) {

            // val[0] -> max prefix sum
            // val[1] -> max suffix sum
            long[] val = new long[2];

            // Find prefix and suffix maximums
            IndMax(arr, 0, len, val);

            // Add best prefix and suffix
            ans2 += val[0];
            ans2 += val[1];

            // Add contribution of middle (k-2) arrays
            ans2 += ((k - 2) * total) % MOD;
        }

        // Return maximum of both cases with modulo
        return (int) Math.max(ans1, ans2) % MOD;
    }

    public void IndMax(int[] arr, int st, int ed, long[] val) {

        // max1 -> maximum prefix sum
        long max1 = 0;

        // sum1 -> running prefix sum
        long sum1 = 0;

        // max2 -> maximum suffix sum
        long max2 = 0;

        // sum2 -> running suffix sum
        long sum2 = 0;

        // Traverse from start and end simultaneously
        for (int i = st; i < ed; i++) {

            // Prefix sum calculation
            sum1 += arr[i];
            max1 = Math.max(max1, sum1);

            // Suffix sum calculation (from end)
            sum2 += arr[ed - 1 - i];
            max2 = Math.max(max2, sum2);
        }

        // Store results
        val[0] = max1;
        val[1] = max2;
    }

    public long finderMax(int[] arr, int len, int lim) {

        // Current running sum (Kadane)
        long sum = 0;

        // Maximum subarray sum
        long max = 0;

        // Total sum of processed elements
        long ttl = 0;

        // Traverse up to 'len' (can simulate concatenation using modulo)
        for (int i = 0; i < len; i++) {

            // Add current element (using modulo for repetition)
            sum += arr[i % lim];

            // Reset sum if it becomes negative (Kadane's logic)
            if (sum < 0) sum = 0;

            // Update maximum subarray sum
            max = Math.max(max, sum);

            // Track total sum
            ttl += arr[i % lim];
        }

        // Store total sum of original array
        total = ttl / 2;

        // Return maximum subarray sum found
        return max;
    }
}