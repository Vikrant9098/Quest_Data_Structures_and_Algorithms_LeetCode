class Solution {
    // Solution class

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        // Method to find all pairs with minimum absolute difference

        List<List<Integer>> res = new ArrayList<>();
        // Stores the result pairs

        // In a sorted array, adjacent elements have the smallest difference
        Arrays.sort(arr);
        // Sort the array

        int min = Integer.MAX_VALUE;
        // Stores the minimum difference found so far

        for (int i = 0; i < arr.length - 1; i++) {
            // Loop through adjacent elements

            int diff = arr[i + 1] - arr[i];
            // Calculate difference between adjacent elements

            if (diff < min) {
                // Found a new smaller minimum difference

                min = diff;
                // Update minimum difference

                res.clear();
                // Clear old pairs

                res.add(Arrays.asList(arr[i], arr[i + 1]));
                // Add the new pair with minimum difference
            } 
            else if (diff == min) {
                // Found another pair with the same minimum difference

                res.add(Arrays.asList(arr[i], arr[i + 1]));
                // Add this pair to the result
            }
        }

        return res;
        // Return all pairs with minimum absolute difference
    }
}
