class Solution(object):                 # Define a class named Solution
    def rotateString(self, s, goal):    # Define function to check rotation
        """
        :type s: str                    # s is a string
        :type goal: str                 # goal is a string
        :rtype: bool                    # return value is True or False
        """
        if len(s) != len(goal):         # If lengths are different, rotation is impossible
            return False                # Return False

        return goal in s + s            # Check if goal exists in s+s (all rotations)
