class MyQueue(object):                     # Define Queue using two stacks
    def __init__(self):                    # Constructor
        self.in_stk = []                   # Stack used for adding elements
        self.out_stk = []                  # Stack used for removing elements

    # Push element x to the back of queue...
    def push(self, x):                     # Add an element to the queue
        self.in_stk.append(x)              # Push element into input stack

    # Remove the element from the front of the queue and returns it...
    def pop(self):                         # Remove front element
        self.peek()                        # Make sure output stack has elements
        return self.out_stk.pop()          # Pop and return front element

    # Get the front element...
    def peek(self):                        # View the front element
        if not self.out_stk:               # If output stack is empty
            while self.in_stk:             # Move all elements from input stack
                self.out_stk.append(self.in_stk.pop())  # Push into output stack
        return self.out_stk[-1]             # Return the front element

    # Return whether the queue is empty...
    def empty(self):                       # Check if queue is empty
        return not self.in_stk and not self.out_stk  # True if both stacks are empty
