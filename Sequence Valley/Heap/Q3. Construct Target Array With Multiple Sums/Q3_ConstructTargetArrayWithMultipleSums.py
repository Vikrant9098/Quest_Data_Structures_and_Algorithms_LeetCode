class Solution(object):
    def isPossible(self, target):
        """
        :type target: List[int]
        :rtype: bool
        """
        heap = [-num for num in target]        # Create max heap using negative values
        total = sum(target)                    # Store sum of all elements
        heapify(heap)                          # Convert list into a heap

        while heap[0] != -1:                   # Continue until all values become 1
            num = -heappop(heap)               # Get the largest value
            total -= num                       # Remove it from total sum

            if num <= total or total < 1:      # Check if operation is invalid
                return False                   # Not possible to reach target

            num %= total                       # Reduce the largest value
            total += num                       # Add updated value to total

            heappush(heap, -num or -total)     # Push updated value back into heap

        return True                            # Target array is possible
