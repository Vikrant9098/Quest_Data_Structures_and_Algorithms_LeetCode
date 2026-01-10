# Solution class
class Solution:

    # Function to calculate exclusive execution time
    def exclusiveTime(self, n, logs):

        # Result array to store execution time for each function
        res = [0] * n

        # Stack to track active functions
        stack = []

        # Previous timestamp tracker
        prev_time = 0

        # Loop through each log
        for log in logs:

            # Split log into function id, status, and time
            func_id, status, time = log.split(':')

            # Convert id and time to integers
            func_id, time = int(func_id), int(time)

            # If function starts
            if status == 'start':

                # If some function is running, add time difference to it
                if stack:
                    res[stack[-1]] += time - prev_time

                # Push current function onto stack
                stack.append(func_id)

                # Update previous time
                prev_time = time

            else:  # If function ends

                # Pop function and add its execution time (+1 for inclusive)
                res[stack.pop()] += time - prev_time + 1

                # Update previous time to next moment
                prev_time = time + 1

        # Return final execution times
        return res
