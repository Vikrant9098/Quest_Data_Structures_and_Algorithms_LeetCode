class Solution(object):
    def getConcatenation(self, nums):
        # Create a new list to store the concatenated list
        answer = [0] * (2 * len(nums))
        j = 0

        # Iterate through the original list
        for i in range(len(nums)):
            # Add the current number to the new list
            answer[i] = nums[i]
            # Add the current number again at the corresponding position after the original numbers
            answer[i + len(nums)] = nums[i]

        # Return the concatenated list
        return answer
