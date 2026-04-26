class Solution {
    public int maxArea(int[] height) 
    {
        int left = 0; // Initialize left pointer at the beginning of the array
        int right = height.length - 1; // Initialize right pointer at the end of the array

        int maxArea = 0; // Variable to store the maximum area found

        // Run the loop until both pointers meet
        while (left < right) 
        {
            int width = right - left; // Calculate the width between two pointers

            // Choose the smaller height between left and right
            int minHeight = Math.min(height[left], height[right]);

            // Calculate current area using width and minimum height
            int currentArea = width * minHeight;

            // Update maxArea if current area is larger
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer which has smaller height
            // Because increasing height may give a better area
            if (height[left] < height[right]) 
            {
                left++; // Move left pointer to the right
            } 
            else 
            {
                right--; // Move right pointer to the left
            }
        }

        return maxArea; // Return the maximum area found
    }
}