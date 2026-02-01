class Solution(object):
    def reversePairs(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        def recursiveFunction(lowerIndex=0, upperIndex=len(nums) - 1):
            # Recursive function to count reverse pairs in a subarray
            
            if lowerIndex >= upperIndex:     # If subarray has 0 or 1 element
                return 0                     # No reverse pairs possible
            
            midIndex = (lowerIndex + upperIndex) // 2  # Find middle index
            
            # Count reverse pairs in left and right halves
            count = (recursiveFunction(lowerIndex, midIndex) +
                     recursiveFunction(midIndex + 1, upperIndex))
            
            index_i = lowerIndex             # Pointer for left half
            
            # Check reverse pairs across left and right halves
            for rightNumber in nums[midIndex + 1: upperIndex + 1]:
                while index_i <= midIndex and nums[index_i] <= rightNumber * 2:
                    index_i += 1             # Move pointer if condition not met
                count += midIndex + 1 - index_i  # Add valid reverse pairs
                if index_i > midIndex:       # If left part exhausted
                    break
            
            # Sort the current subarray for merge step
            nums[lowerIndex: upperIndex + 1] = sorted(nums[lowerIndex: upperIndex + 1])
			
            return count                     # Return total count for this subarray
        
        return recursiveFunction()           # Start recursion on full array
