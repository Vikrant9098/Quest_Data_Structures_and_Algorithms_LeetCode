class Solution {                                // Define Solution class
    public int waysToMakeFair(int[] nums) {     // Method to count fair indices
        int esum = 0;                           // Sum of elements at even positions after removal
        int osum = 0;                           // Sum of elements at odd positions after removal
        int n = nums.length;                    // Store length of the array

        for (int i = 0; i < n; i++) {            // Loop through the array
            if (i % 2 == 0) {                    // If index is even
                osum += nums[i];                 // Add value to odd sum (will shift after removal)
            } else {                             // If index is odd
                esum += nums[i];                 // Add value to even sum (will shift after removal)
            }
        }

        int count = 0;                           // Stores number of fair indices
        int prev = 0;                            // Stores previous element

        for (int i = 0; i < n; i++) {            // Loop through each index
            if (i % 2 == 0) {                    // If current index is even
                osum = osum - nums[i] + prev;    // Update odd sum after removing nums[i]
            } else {                             // If current index is odd
                esum = esum - nums[i] + prev;    // Update even sum after removing nums[i]
            }

            if (esum == osum) {                  // Check if array becomes fair
                count++;                         // Increase fair index count
            }

            prev = nums[i];                      // Update previous element
        }

        return count;                            // Return total fair indices
    }
}
