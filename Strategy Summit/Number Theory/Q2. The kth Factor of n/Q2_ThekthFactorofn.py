class Solution(object):
    def kthFactor(self, n, k):
        # Initialize a counter to keep track of factors found
        count = 0
        
        # Loop from 1 to n (inclusive) to check all possible factors
        for i in range(1, n + 1):
            
            # Check if i is a factor of n
            if n % i == 0:
                # Increment the factor count
                count += 1
                
                # If this is the k-th factor, return it
                if count == k:
                    return i
        
        # If fewer than k factors exist, return -1
        return -1