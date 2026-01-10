# Solution class
class Solution(object):

    # Function to build the operations list
    def buildArray(self, target, n):

        # List to store operations
        result = []

        # Current number to process
        current = 1  # starts from 1

        # Loop through each number in target
        for num in target:

            # If current number is smaller than target number
            while current < num:

                # Add "Push" and "Pop" operations
                result.extend(["Push", "Pop"])

                # Move to next number
                current += 1

            # When current equals target number, push it
            result.append("Push")

            # Move to next number
            current += 1

        # Return the list of operations
        return result
