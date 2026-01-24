class Solution(object):
    # Define Solution class

    def reductionOperations(self, nums):
        # Function to count total reduction operations

        nums.sort()
        # Sort the array in ascending order

        size = len(nums)
        # Store total number of elements

        ans = 0
        # Variable to store total operations

        for i in range(size - 1, 0, -1):
            # Loop from last index to first

            if nums[i - 1] != nums[i]:
                # Check if current and previous values are different

                ans += size - i
                # Add count of smaller elements to operations

        return ans
        # Return total operations
