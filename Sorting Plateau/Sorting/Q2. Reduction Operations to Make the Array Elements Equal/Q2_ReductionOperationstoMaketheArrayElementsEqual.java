class Solution {
    // Solution class

    public int reductionOperations(int[] nums) {
        // Method to count total reduction operations

        Arrays.sort(nums);
        // Sort the array in ascending order

        int si = nums.length;
        // Store total number of elements

        int ans = 0;
        // Stores total operations count

        for (int i = nums.length - 1; i > 0; i--) {
            // Loop from largest element to smallest

            if (nums[i - 1] != nums[i]) {
                // Check if current and previous elements are different

                ans += si - i;
                // Add number of smaller elements to operations
            }
        }

        return ans;
        // Return total operations
    }
}