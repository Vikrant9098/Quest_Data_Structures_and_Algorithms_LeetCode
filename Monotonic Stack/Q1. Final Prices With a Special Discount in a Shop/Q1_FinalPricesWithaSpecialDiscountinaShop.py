# Solution class
class Solution:

    # Function to calculate final prices after discount
    def finalPrices(self, prices):

        # Length of the array
        n = len(prices)

        # nge[i] stores index of next smaller or equal price for prices[i]
        nge = [-1] * n

        # Stack to keep track of indices while scanning
        st = []

        # Start from the last index
        st.append(n - 1)

        # Traverse array from second last to first
        for i in range(n - 2, -1, -1):

            # Pop indices whose prices are greater than current price
            while st and prices[st[-1]] > prices[i]:
                st.pop()

            # If stack not empty, next smaller/equal price exists
            if st:
                nge[i] = st[-1]

            # Push current index onto stack
            st.append(i)

        # Array to store final discounted prices
        ans = [0] * n

        # Calculate final price for each item
        for i in range(n):

            # If no smaller/equal price ahead, price stays same
            if nge[i] == -1:
                ans[i] = prices[i]

            # Else subtract the discount price
            else:
                ans[i] = prices[i] - prices[nge[i]]

        # Return the final prices
        return ans
