class Solution(object):                  # Define Solution class

    def search(self, nums, target):      # Function to search target

        """
        :type nums: List[int]            # nums is a list of integers
        :type target: int                # target is an integer
        :rtype: int                      # return type is integer
        """

        lo = 0                           # Start index
        hi = len(nums) - 1               # End index

        while lo <= hi:                  # Loop while range is valid

            mid = (lo + hi) // 2         # Find middle index

            if nums[mid] == target:      # If target found
                return mid               # Return index

            if target > nums[mid]:       # If target is bigger
                lo = mid + 1             # Search right side
            else:                        # If target is smaller
                hi = mid - 1             # Search left side

        return -1                        # Return -1 if not found
