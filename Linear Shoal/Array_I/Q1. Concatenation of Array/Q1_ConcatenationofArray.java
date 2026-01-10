// Solution class
class Solution {

    // Method to concatenate array with itself
    public int[] getConcatenation(int[] nums) {

        // Store length of input array
        int len = nums.length;

        // Create new array of size 2 * len
        int[] ans = new int[2 * len];

        // Loop through original array
        for (int i = 0; i < len; i++) {

            // Copy element to first half
            ans[i] = nums[i];

            // Copy element to second half
            ans[i + len] = nums[i];
        }

        // Return the concatenated array
        return ans;
    }
}
