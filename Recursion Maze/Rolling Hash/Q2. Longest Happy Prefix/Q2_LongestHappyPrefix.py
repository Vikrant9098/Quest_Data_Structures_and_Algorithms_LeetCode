class Solution(object):
    def longestPrefix(self, s):
        """
        :type s: str
        :rtype: str
        """

        dp = [0] * len(s)          # dp[i] stores length of longest prefix = suffix till index i

        length, i = 0, 1           # length = current prefix match length, i = current index

        while i < len(s):          # Traverse the string

            if s[i] == s[length]:  # If current characters match
                length += 1        # Increase matched prefix length
                dp[i] = length     # Store match length at index i
                i += 1             # Move to next character

            else:                  # If characters do not match

                if length > 0:     # If previous matches exist
                    length = dp[length - 1]  # Fall back to shorter prefix
                else:              # If no match at all
                    dp[i] = 0      # No prefix-suffix match here
                    i += 1         # Move forward

        return s[:dp[-1]]          # Return longest prefix which is also suffix
