// Solution class
class Solution {

    // Method to shuffle the array
    public int[] shuffle(int[] nums, int n) {

        // Store total length of the array
        int len = nums.length;

        // Loop through the second half of the array
        // This packs two numbers into one position
        for (int i = n; i < len; i++) {

            // Store nums[i] and nums[i - n] together using multiplication
            nums[i] = (nums[i] * 1024) + nums[i - n];
        }

        // Index to place shuffled elements
        int index = 0;

        // Loop again through the packed values
        for (int i = n; i < len; i++, index += 2) {

            // Extract the smaller number using modulo
            nums[index] = nums[i] % 1024;

            // Extract the bigger number using division
            nums[index + 1] = nums[i] / 1024;
        }

        // Return the shuffled array
        return nums;
    }
}
