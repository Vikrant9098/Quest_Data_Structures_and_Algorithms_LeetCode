class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0, maxCount = 0, maxLength = 0;
        int[] freq = new int[26]; // Array to store frequency of characters

        for (int right = 0; right < s.length(); right++) {
            // Increase the count of the current character
            freq[s.charAt(right) - 'A']++;

            // Find the most frequent character in the current window
            maxCount = Math.max(maxCount, freq[s.charAt(right) - 'A']);

            // Check if we need to shrink the window
            if ((right - left + 1) - maxCount > k) {
                // Remove the leftmost character from the window
                freq[s.charAt(left) - 'A']--;
                left++; // Move left pointer forward
            }

            // Update the maximum length of a valid substring
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;

    }
}