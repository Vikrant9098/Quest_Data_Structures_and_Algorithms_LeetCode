class Solution:                               # Defines the Solution class
    def repeatedStringMatch(self, a, b):      # Method to find minimum repeats of 'a'
        repeat = (len(b) // len(a))            # Estimate minimum repeats needed
        count = 1                              # Start counting repetitions from 1
        while count <= repeat + 2:             # Loop with a small extra buffer
            if b in a * count:                 # Check if b is a substring of repeated a
                return count                   # Return number of repetitions
            else:
                count += 1                     # Increase repeat count
        return -1                              # Return -1 if b is not found
