class Solution(object):                          # Define the Solution class
    def timeRequiredToBuy(self, tickets, k):     # Method to calculate total time
        """
        :type tickets: List[int]                 # List of tickets needed by each person
        :type k: int                             # Index of the target person
        :rtype: int                              # Return total time
        """
        c = 0                                    # Counter to store time spent
        
        while True:                              # Run until target person finishes
            if tickets[k] == 0:                  # If target person has no tickets left
                break                            # Stop the loop
            
            for i in range(len(tickets)):        # Loop through all people
                if tickets[k] == 0:              # If target person is done
                    break                        # Stop the loop
                if tickets[i] > 0:               # If current person needs tickets
                    tickets[i] -= 1              # Give one ticket
                    c += 1                       # Increase time by 1 second
        
        return c                                 # Return total time taken
