// Solution class
class Solution {

    // Method to count smaller numbers than current
    public int[] smallerNumbersThanCurrent(int[] nums) {

        // Array to store the result
        int[] output = new int[nums.length];

        // Count array for numbers 0 to 100
        int[] count = new int[101]; // numbers range from 0 to 100

        // Count how many times each number appears
        for (int num : nums) {

            // Increase count of current number
            count[num]++;
        }

        // Convert count array to prefix sum
        for (int i = 1; i <= 100; i++) {

            // Add previous count to current
            count[i] += count[i - 1];
        }

        // Calculate result for each element
        for (int i = 0; i < nums.length; i++) {

            // If number is 0, no smaller numbers exist
            // Else get count of numbers smaller than nums[i]
            output[i] = nums[i] == 0 ? 0 : count[nums[i] - 1];
        }

        // Return the result array
        return output;
    }
}
