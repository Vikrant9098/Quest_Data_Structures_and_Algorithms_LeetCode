class Solution(object):
    def findKthBit(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """

        # Base case:
        # When n == 1, the string S1 = "0"
        # So we directly return '0'
        if n == 1:
            return '0'
        
        # Length of Sn is (2^n - 1)
        # (1 << n) means 2^n
        length = (1 << n) - 1
        
        # Middle position of the string
        # Since length is always odd:
        # middle = length // 2 + 1
        mid = length // 2 + 1
        
        # Important rule:
        # The middle element of every Sn is always '1'
        if k == mid:
            return '1'
        
        # If k is in the first half,
        # the first half is exactly same as Sn-1
        # So we recursively search in Sn-1
        if k < mid:
            return self.findKthBit(n - 1, k)
        
        # If k is in the second half:
        # Second half = reverse(invert(Sn-1))
        # So:
        # 1) Find the mirrored index in Sn-1 -> (length - k + 1)
        # 2) Recursively find that bit
        # 3) Invert the result
        
        bit = self.findKthBit(n - 1, length - k + 1)
        
        # Invert the bit: '0' becomes '1', '1' becomes '0'
        return '1' if bit == '0' else '0'