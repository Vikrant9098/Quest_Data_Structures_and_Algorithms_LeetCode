class Solution(object):                      # Define Solution class
    def minSubarray(self, nums, p):           # Function to find minimum subarray
        """
        :type nums: List[int]                 # nums is a list of integers
        :type p: int                          # p is the given divisor
        :rtype: int                           # function returns an integer
        """
        totalSum = sum(nums)                  # Calculate sum of all elements
        rem = totalSum % p                    # Find remainder when divided by p

        if rem == 0:                          # If sum already divisible by p
            return 0                          # No need to remove any subarray

        prefixMod = {0: -1}                   # Store prefix mod value with index
        prefixSum = 0                         # Initialize prefix sum
        minLength = len(nums)                 # Initialize minimum length

        for i, num in enumerate(nums):        # Loop through array with index
            prefixSum += num                  # Add current number to prefix sum
            currentMod = prefixSum % p        # Get current prefix modulo p
            targetMod = (currentMod - rem + p) % p  # Required modulo to remove

            if targetMod in prefixMod:        # If required modulo exists
                minLength = min(minLength, i - prefixMod[targetMod])  
                                              # Update minimum subarray length

            prefixMod[currentMod] = i         # Store current modulo with index

        return minLength if minLength < len(nums) else -1  
                                              # Return result or -1 if not possible
