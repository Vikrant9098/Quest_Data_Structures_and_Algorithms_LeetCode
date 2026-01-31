class Solution 
{
    public int numberOfSubstrings(String s) 
    {
        int[] count = new int[3];  // To store occurrences of 'a', 'b', 'c'

        int left = 0, result = 0;

        for (int right = 0; right < s.length(); right++) 
        {
            count[s.charAt(right) - 'a']++; // Increase count of current character

            //s.charAt(right) - 'a' converts the character at index right into an integer              index (0, 1, or 2) for the array.
            // Explanation
            // 'a' - 'a' = 0 → Represents 'a'
            // 'b' - 'a' = 1 → Represents 'b'
            // 'c' - 'a' = 2 → Represents 'c'
            // This is used to map characters 'a', 'b', and 'c' to indices 0, 1, and 2 in the count array.

            // Check if the window contains at least one 'a', 'b', and 'c'
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) 
            {
                count[s.charAt(left) - 'a']--; // Shrink the window from the left
                left++; 
            }

            result += left; // Add the number of valid substrings ending at 'right'
        }

        return result;
        
    }
}