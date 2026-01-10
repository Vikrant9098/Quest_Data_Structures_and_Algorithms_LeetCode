class Solution 
{
    public int findMaxConsecutiveOnes(int[] nums) 
    {
        int maxCount = 0;  // Stores the maximum consecutive 1s found
        int count = 0;     // Counts the current streak of 1s

        for (int num : nums) 
        {
            if (num == 1) 
            {
                count++;  // Increase count when 1 is found
                maxCount = Math.max(maxCount, count);  // Update maxCount if needed
            }
            else 
            {
                count = 0; // Reset count when 0 is found
            }
        }

        return maxCount;
    }
}
