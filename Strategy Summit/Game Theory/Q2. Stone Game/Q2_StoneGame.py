class Solution(object):
    def stoneGame(self, piles):
        """
        :type piles: List[int]
        :rtype: bool
        """
        # Key observation:
        # When the number of piles is even and the total number of stones is odd,
        # the first player (Alex) can always guarantee a win.
        
        # Reason:
        # Alex can choose either all even-indexed piles or all odd-indexed piles.
        # Since the total number of piles is even, one of these choices will have
        # a strictly greater sum, and Alex can force picking those piles.
        
        # Therefore, the result is always True
        return True