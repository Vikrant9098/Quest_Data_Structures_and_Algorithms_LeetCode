class Solution(object):
    def lemonadeChange(self, bills):
        """
        :type bills: List[int]
        :rtype: bool
        """

        # Count how many $5 bills we currently have
        five_dollar_bills = 0
        
        # Count how many $10 bills we currently have
        ten_dollar_bills = 0

        # Iterate over each customer's payment
        for customer_bill in bills:

            # Case 1: Customer pays with $5 → no change needed
            if customer_bill == 5:
                five_dollar_bills += 1  # Simply take the $5 bill

            # Case 2: Customer pays with $10 → give $5 as change
            elif customer_bill == 10:

                # Check if we have at least one $5 bill to give as change
                if five_dollar_bills > 0:
                    five_dollar_bills -= 1   # Give one $5
                    ten_dollar_bills += 1    # Receive one $10
                else:
                    return False  # Cannot give change → fail

            # Case 3: Customer pays with $20 → need to give $15 change
            else:  # customer_bill == 20

                # Prefer giving one $10 + one $5 (optimal strategy)
                if ten_dollar_bills > 0 and five_dollar_bills > 0:
                    ten_dollar_bills -= 1   # Give one $10
                    five_dollar_bills -= 1  # Give one $5

                # Otherwise, give three $5 bills
                elif five_dollar_bills >= 3:
                    five_dollar_bills -= 3  # Give three $5 bills

                else:
                    return False  # Cannot give $15 change → fail

        # If all customers were handled successfully
        return True