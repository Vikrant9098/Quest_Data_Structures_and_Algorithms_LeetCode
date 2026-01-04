# Solution class
class Solution(object):

    # Function to find missing numbers
    def findDisappearedNumbers(self, nums):

        # Store length of the list
        n = len(nums)

        # Loop through each index
        for i in range(n):

            # Place numbers in correct position using swapping
            while nums[i] != nums[nums[i] - 1]:

                # Swap current number with its correct position
                nums[nums[i] - 1], nums[i] = nums[i], nums[nums[i] - 1]

        # Collect numbers that are not in correct position
        return [i + 1 for i in range(n) if nums[i] != i + 1]
