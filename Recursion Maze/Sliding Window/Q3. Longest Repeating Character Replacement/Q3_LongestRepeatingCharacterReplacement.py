class Solution(object):
    def characterReplacement(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: int
        """

        l = 0                         # Left pointer of sliding window
        c_frequency = {}              # Dictionary to store character counts
        longest_str_len = 0           # Stores maximum valid window length

        for r in range(len(s)):       # Right pointer moves through string

            if s[r] not in c_frequency:   # If character not seen before
                c_frequency[s[r]] = 0     # Initialize its count

            c_frequency[s[r]] += 1        # Increase count of current character

            # Total characters inside current window
            cells_count = r - l + 1

            # Check if we can replace characters within k limit
            if cells_count - max(c_frequency.values()) <= k:
                longest_str_len = max(longest_str_len, cells_count)  
                # Update longest valid window

            else:
                c_frequency[s[l]] -= 1    # Reduce count of left character
                if c_frequency[s[l]] == 0:  # If count becomes zero
                    c_frequency.pop(s[l])   # Remove character from dictionary
                l += 1                    # Move left pointer forward

        return longest_str_len            # Return the longest valid length
