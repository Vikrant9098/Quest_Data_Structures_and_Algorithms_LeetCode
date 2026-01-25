class Solution(object):                 # Define the Solution class
    def judgeSquareSum(self, c):        # Function to check sum of two squares
        divisor = 2                     # Start checking from 2
        while divisor * divisor <= c:   # Loop till √c
            if c % divisor == 0:        # Check if divisor divides c
                exponentCount = 0       # Count power of this divisor
                while c % divisor == 0: # Remove all occurrences of divisor
                    exponentCount += 1  # Increase power count
                    c //= divisor       # Divide c by divisor
                if divisor % 4 == 3 and exponentCount % 2 != 0:  # Invalid case
                    return False        # Cannot be written as sum of squares
            divisor += 1                # Move to next divisor
        return c % 4 != 3               # Final check for remaining prime
