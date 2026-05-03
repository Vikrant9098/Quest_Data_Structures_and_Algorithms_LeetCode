class Solution(object):
    
    def prime(self, n):
        # Check if number is less than 2 (not prime)
        if n < 2:
            return False
        
        # 2 is the only even prime number
        if n == 2:
            return True
        
        # Check divisibility from 2 to sqrt(n)
        # If any number divides n, then n is not prime
        for i in range(2, int(n ** 0.5) + 1):
            if n % i == 0:
                return False
        
        # If no divisors found, n is prime
        return True

    def maximumPrimeDifference(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        # List to store indices of prime numbers in nums
        arr = []
        
        # Traverse the array
        for i in range(len(nums)):
            
            # Check if current element is prime
            if self.prime(nums[i]):
                
                # Store its index if it is prime
                arr.append(i)
        
        # Return the difference between last and first prime index
        return arr[-1] - arr[0]