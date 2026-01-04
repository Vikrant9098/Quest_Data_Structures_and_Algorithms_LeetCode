class Solution(object):
    def lastStoneWeight(self, stones):
        """
        :type stones: List[int]
        :rtype: int
        """
        for i in range(len(stones) - 1):          # Loop until one stone is left
            stones.sort()                         # Sort stones to get the heaviest at the end
            stones[-2] = abs(stones[-2] - stones[-1])  # Smash two heaviest stones
            stones.pop()                          # Remove the heaviest stone
        
        return stones[0]                          # Return the last remaining stone
