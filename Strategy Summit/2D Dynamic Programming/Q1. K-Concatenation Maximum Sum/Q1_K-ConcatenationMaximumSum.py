class Solution(object):
    def kConcatenationMaxSum(self, arr, k):
        """
        :type arr: List[int]
        :type k: int
        :rtype: int
        """

        # Modulo value to avoid overflow
        MOD = 10**9 + 7

        # Kadane's Algorithm to find maximum subarray sum
        def kadane(nums):

            # max_sum stores the overall maximum subarray sum
            # curr stores current running subarray sum
            max_sum = curr = 0

            # Traverse through numbers
            for num in nums:

                # Either extend current subarray or start new from 0
                curr = max(0, curr + num)

                # Update maximum sum found so far
                max_sum = max(max_sum, curr)

            return max_sum

        # Maximum subarray sum for single array
        max_single = kadane(arr)

        # If only one concatenation, return result directly
        if k == 1:
            return max_single % MOD

        # Total sum of the array
        total_sum = sum(arr)

        # Maximum subarray sum for two concatenated arrays
        double_kadane = kadane(arr * 2)

        # If total sum is positive, middle arrays contribute positively
        if total_sum > 0:

            # Add contribution of (k-2) middle arrays
            return (double_kadane + (k - 2) * total_sum) % MOD

        else:
            # Otherwise, best result is within two concatenations
            return double_kadane % MOD