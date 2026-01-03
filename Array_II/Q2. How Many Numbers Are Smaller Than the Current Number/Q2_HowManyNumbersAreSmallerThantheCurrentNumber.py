# Solution class
class Solution(object):

    # Function to find duplicate and missing numbers
    def findErrorNums(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """

        # Get the length of the array
        n = len(nums)

        # Calculate sum of numbers from 1 to n
        s = n * (n + 1) // 2

        # Find missing number using set (removes duplicate)
        miss = s - sum(set(nums))

        # Find duplicate number using sum difference
        duplicate = sum(nums) + miss - s

        # Return duplicate and missing numbers
        return [duplicate, miss]
