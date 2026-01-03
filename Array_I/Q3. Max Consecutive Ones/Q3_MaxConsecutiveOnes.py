# Solution class
class Solution(object):

    # Function to find maximum consecutive 1s
    def findMaxConsecutiveOnes(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """

        # maxi stores the maximum count of consecutive 1s
        maxi = 0

        # result stores current consecutive 1s count
        result = 0

        # Loop through each number in nums
        for num in nums:

            # If current number is 1
            if num == 1:

                # Increase current count
                result += 1

                # Update maximum if needed
                maxi = max(maxi, result)

            # If current number is 0
            else:

                # Reset current count
                result = 0

        # Return the maximum consecutive 1s
        return maxi
