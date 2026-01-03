# Solution class
class Solution(object):

    # Function to shuffle the array
    def shuffle(self, nums, n):

        # Create empty list to store result
        l = []

        # Store first n elements (x part)
        l1 = nums[:n]

        # Store remaining elements (y part)
        l2 = nums[n:]

        # Loop n times
        for i in range(n):

            # Add element from first list
            l.append(l1[i])

            # Add element from second list
            l.append(l2[i])

        # Return the shuffled list
        return l
