class Solution(object):                   # Define Solution class

    def magicalString(self, n):           # Function to count number of 1s in magical string
        """
        :type n: int                      # n is an integer
        :rtype: int                      # function returns an integer
        """

        if n == 0:                        # If n is 0
            return 0                     # No characters, return 0

        s = [1, 2, 2]                    # Initialize magical string list

        i = 2                            # Pointer to read repetition count

        while len(s) < n:                # Build string until length reaches n

            s += [3 - s[-1]] * s[i]      # Append opposite number (1↔2) s[i] times

            i += 1                       # Move pointer to next index

        return s[:n].count(1)            # Count number of 1s in first n elements
