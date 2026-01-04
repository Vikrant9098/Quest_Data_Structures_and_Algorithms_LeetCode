// Solution class
class Solution {

    // Method to calculate final prices after discount
    public int[] finalPrices(int[] prices) {

        // Length of the array
        int n = prices.length;

        // Result array to store final prices
        int[] res = new int[n];

        // Loop through each item
        for (int i = 0; i < n; i++) {

            // Initialize result with original price
            res[i] = prices[i];

            // Check next items for discount
            for (int j = i + 1; j < n; j++) {

                // If next price is less than or equal to current
                if (prices[j] <= prices[i]) {

                    // Apply discount
                    res[i] -= prices[j];

                    // Stop after first eligible discount
                    break;
                }
            }
        }

        // Return final prices
        return res;
    }
}
