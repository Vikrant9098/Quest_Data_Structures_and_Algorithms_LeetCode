class Solution(object):
    def findGCD(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """

        # Get the minimum and maximum elements from the array
        a, b = min(nums), max(nums)

        # Use subtraction-based Euclidean Algorithm
        # Keep reducing until both numbers become equal
        while a != b:

            # Subtract smaller number from larger number
            b = b - a

            # Reassign a as smaller and b as larger again
            a, b = min(a, b), max(a, b)

        # When both are equal, that value is the GCD
        return a