class Solution {                           // Define the Solution class

    public int threeSumClosest(int[] nums, int target) {  
                                             // Function to find 3 numbers whose sum is closest to target

        Arrays.sort(nums);                   // Sort the array to use two-pointer technique

        int closest_sum = Integer.MAX_VALUE / 2;  
                                             // Store closest sum found so far (large value to avoid overflow)

        for (int i = 0; i < nums.length - 2; ++i) {  
                                             // Loop through each number as the first element

            int left = i + 1, right = nums.length - 1;  
                                             // Set two pointers after i and at end

            while (left < right) {            // Continue until pointers cross

                int current_sum = nums[i] + nums[left] + nums[right];  
                                             // Calculate sum of current triplet

                if (Math.abs(current_sum - target) < Math.abs(closest_sum - target)) {
                                             // Check if this sum is closer to target
                    closest_sum = current_sum; // Update closest sum
                }

                if (current_sum < target) {   // If sum is smaller than target
                    ++left;                    // Move left pointer to increase sum
                } 
                else if (current_sum > target) {  
                                             // If sum is greater than target
                    --right;                   // Move right pointer to decrease sum
                } 
                else {                         // If sum equals target
                    return current_sum;        // Exact match found, return it
                }
            }
        }

        return closest_sum;                   // Return the closest sum after checking all
    }
}
