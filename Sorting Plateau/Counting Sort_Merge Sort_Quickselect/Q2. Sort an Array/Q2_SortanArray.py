class Solution(object):

    # Function to merge two sorted subarrays:
    # A[start .. mid] and A[mid+1 .. end]
    def merge(self, A, start, mid, end, buff):

        left, right = start, mid + 1     # Pointers for left and right subarrays
        s = end - start + 1              # Total number of elements to merge

        # Merge elements into buffer array
        for i in range(s):

            i0 = start + i               # Actual index in buffer / array

            # If left subarray is exhausted
            if left > mid:
                buff[i0] = A[right]      # Take element from right subarray
                right += 1               # Move right pointer

            # If right subarray is exhausted
            elif right > end:
                buff[i0] = A[left]       # Take element from left subarray
                left += 1                # Move left pointer

            # If left element is smaller
            elif A[left] < A[right]:
                buff[i0] = A[left]       # Copy left element
                left += 1                # Move left pointer

            # If right element is smaller or equal
            else:
                buff[i0] = A[right]      # Copy right element
                right += 1               # Move right pointer

        # Copy merged elements back to original array
        for i in range(start, start + s):
            A[i] = buff[i]

    # Recursive merge sort function
    def mergeSort(self, A, start, end, buff):

        # Base case: single element or invalid range
        if end <= start:
            return

        # Find middle index
        mid = start + (end - start) // 2

        # Sort left half
        self.mergeSort(A, start, mid, buff)

        # Sort right half
        self.mergeSort(A, mid + 1, end, buff)

        # Merge both sorted halves
        self.merge(A, start, mid, end, buff)

    # Function called by LeetCode
    def sortArray(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """

        # Temporary buffer array to avoid repeated allocations
        buff = [0] * len(nums)

        # Apply merge sort on entire array
        self.mergeSort(nums, 0, len(nums) - 1, buff)

        # Return sorted array
        return nums
