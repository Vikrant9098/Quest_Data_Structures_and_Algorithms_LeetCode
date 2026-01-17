class Solution(object):
    def repeatedSubstringPattern(self, s):
        """
        :type s: str
        :rtype: bool
        """
        n = len(s)                          # Get length of the string
        
        for i in range(1, n // 2 + 1):      # Try all possible substring lengths
            if n % i == 0 and s[:i] * (n // i) == s:  # Check if substring repeats
                return True                 # Pattern found
        
        return False                        # No repeating pattern found
