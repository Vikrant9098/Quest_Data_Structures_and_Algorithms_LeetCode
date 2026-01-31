class Solution(object):
    def beautifulArray(self, n):
        """
        :type n: int
        :rtype: List[int]
        """
        nums = list(range(1, n + 1))     # Create list [1, 2, 3, ..., n]
        
        def helper(nums):
            if len(nums) < 3:            # If list has less than 3 elements
                return nums              # It is already beautiful
            
            even = nums[::2]             # Take elements at even indices
            odd = nums[1::2]             # Take elements at odd indices
            
            return helper(even) + helper(odd)  
            # Recursively arrange both parts and merge them
        
        return helper(nums)              # Start recursion on full list
