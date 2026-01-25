class Solution {                          // Define the Solution class

    public int search(int[] nums, int target) { // Function to search target

        int low = 0;                      // Start index of array
        int high = nums.length - 1;       // End index of array

        while (low <= high) {             // Loop while range is valid

            int mid = (low + high) / 2;   // Find middle index

            if (nums[mid] == target) {    // If target is found
                return mid;               // Return index
            }
            else if (nums[mid] > target) { // If middle value is greater
                high = mid - 1;           // Search left side
            }
            else {                        // If middle value is smaller
                low = mid + 1;            // Search right side
            }
        }

        return -1;                        // Return -1 if target not found
    }
}
