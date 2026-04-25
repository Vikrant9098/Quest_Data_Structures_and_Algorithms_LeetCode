class Solution(object):
    def findKthNumber(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: int
        """
        # Start from number 1 (smallest lexicographical number)
        curr = 1
        
        # We already consider '1' as the first number, so reduce k by 1
        k -= 1

        # Loop until we reach the k-th number
        while k > 0:
            # Count how many numbers exist between curr and curr+1 in lexicographical order
            step = self._count_steps(n, curr, curr + 1)

            # If total numbers under this prefix are less than or equal to k
            if step <= k:
                # Skip this entire subtree and move to next prefix
                curr += 1
                k -= step
            else:
                # Go deeper into the subtree (next lexicographical level)
                curr *= 10
                k -= 1  # One step taken downwards

        return curr

    def _count_steps(self, n, prefix1, prefix2):
        """
        Helper function to count numbers between prefix1 and prefix2
        """
        steps = 0

        # Count nodes level by level
        while prefix1 <= n:
            # Add valid numbers between prefix1 and prefix2
            steps += min(n + 1, prefix2) - prefix1
            
            # Move to next level in tree
            prefix1 *= 10
            prefix2 *= 10

        return steps