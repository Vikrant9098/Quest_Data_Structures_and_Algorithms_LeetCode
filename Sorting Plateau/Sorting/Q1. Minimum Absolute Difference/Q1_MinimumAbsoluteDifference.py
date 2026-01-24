class Solution:
    # Define Solution class

    def minimumAbsDifference(self, A):
        # Function to find all pairs with minimum absolute difference

        A.sort()
        # Sort the array in ascending order

        D = [A[i + 1] - A[i] for i in range(len(A) - 1)]
        # Store differences between adjacent elements

        target = min(D)
        # Find the minimum difference

        res = []
        # List to store result pairs

        for i, d in enumerate(D):
            # Loop through all differences with their index

            if d == target:
                # Check if difference equals minimum difference

                res.append([A[i], A[i + 1]])
                # Add the pair having minimum difference

        return res
        # Return all pairs with minimum absolute difference
