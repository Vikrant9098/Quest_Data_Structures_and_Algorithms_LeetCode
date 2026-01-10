// Solution class
class Solution {

    // Method to find the duplicate and missing numbers
    public int[] findErrorNums(int[] nums) {

        // Variable to store duplicate number
        int dup = -1;

        // Variable to store missing number
        int missing = -1;

        // Loop from 1 to n (expected numbers)
        for (int i = 1; i <= nums.length; i++) {

            // Counter to count occurrences of i
            int count = 0;

            // Loop through all elements in nums
            for (int j = 0; j < nums.length; j++) {

                // If nums[j] equals current number i
                if (nums[j] == i) {

                    // Increase the count
                    count++;
                }
            }

            // If number appears twice
            if (count == 2) {

                // Store duplicate number
                dup = i;

            // If number does not appear
            } else if (count == 0) {

                // Store missing number
                missing = i;
            }
        }

        // Return duplicate and missing numbers as array
        return new int[] {dup, missing};
    }
}
