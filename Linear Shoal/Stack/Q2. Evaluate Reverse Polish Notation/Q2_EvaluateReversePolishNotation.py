class Solution(object):
    def evalRPN(self, tokens):
        """
        :type tokens: List[str]
        :rtype: int
        """
        stack = []  # stack to hold numbers during evaluation
        
        for token in tokens:
            if token in {"+", "-", "*", "/"}:
                # Pop the last two numbers
                b = stack.pop()
                a = stack.pop()
                
                # Perform the operation
                if token == "+":
                    stack.append(a + b)
                elif token == "-":
                    stack.append(a - b)
                elif token == "*":
                    stack.append(a * b)
                elif token == "/":
                    # Division should truncate toward zero
                    stack.append(int(a / float(b)))
            else:
                # If token is a number, convert and push to stack
                stack.append(int(token))
        
        # Final result will be the only number left in stack
        return stack.pop()
