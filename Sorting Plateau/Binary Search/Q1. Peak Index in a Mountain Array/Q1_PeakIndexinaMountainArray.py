class Solution:                              # Define the Solution class

    def peakIndexInMountainArray(self, arr): # Function to find peak index

        low = 0                              # Left pointer at start
        high = len(arr) - 1                  # Right pointer at end

        while low < high:                    # Loop until pointers meet

            mid = low + (high - low) // 2    # Find middle index

            if arr[mid] < arr[mid + 1]:      # If next element is bigger
                low = mid + 1                # Peak is on the right side
            elif arr[mid] > arr[mid + 1]:    # If current element is bigger
                high = mid                   # Peak is on left or at mid

        return low                           # Return peak index
