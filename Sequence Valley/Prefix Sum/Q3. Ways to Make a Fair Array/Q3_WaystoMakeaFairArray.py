class Solution(object):
    def waysToMakeFair(self, nums):
        """
        :type nums: List[int]                  # nums is a list of integers
        :rtype: int                            # function returns an integer
        """
        evens = sum(nums[0::2])                # Sum of elements at even indices
        odds = sum(nums[1::2])                 # Sum of elements at odd indices
        ans = 0                                # Count of fair indices
        toggle = 0                             # Tracks index parity

        for n in nums:                         # Loop through each element
            if toggle:                         # If index is odd
                odds -= n                     # Remove n from odd sum
                ans += (evens == odds)         # Check if sums are equal
                evens += n                    # Add n to even sum

            else:                              # If index is even
                evens -= n                    # Remove n from even sum
                ans += (evens == odds)         # Check if sums are equal
                odds += n                     # Add n to odd sum

            toggle ^= 1                        # Switch between even and odd

        return ans                             # Return number of fair indices
