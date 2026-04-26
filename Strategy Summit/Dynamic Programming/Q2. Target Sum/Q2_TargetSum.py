class Q2_TargetSum(object):
    def __init__(self):
        # This variable keeps track of total valid ways to reach the target sum
        self.total_ways = 0

    def findTargetSumWays(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """

        # Start recursion from index 0 with initial sum = 0
        self.calculate_ways(nums, 0, 0, target)

        # Return the total number of valid ways found
        return self.total_ways

    def calculate_ways(self, nums, current_index, current_sum, target):

        # Base case: if we have processed all elements
        if current_index == len(nums):

            # Check if the current sum equals the target
            if current_sum == target:
                self.total_ways += 1  # Found one valid way

        else:
            # Choice 1: Take current number as positive
            self.calculate_ways(
                nums,
                current_index + 1,                     # Move to next index
                current_sum + nums[current_index],     # Add current number
                target
            )

            # Choice 2: Take current number as negative
            self.calculate_ways(
                nums,
                current_index + 1,                     # Move to next index
                current_sum - nums[current_index],     # Subtract current number
                target
            )