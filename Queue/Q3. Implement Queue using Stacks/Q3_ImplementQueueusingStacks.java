import java.util.Stack;                     // Import Stack class

class MyQueue {                             // Define Queue using two stacks
    private Stack<Integer> in_stack;        // Stack for adding elements
    private Stack<Integer> out_stack;       // Stack for removing elements

    public MyQueue() {                      // Constructor
        in_stack = new Stack<>();           // Initialize input stack
        out_stack = new Stack<>();          // Initialize output stack
    }

    private void transfer() {               // Move elements between stacks
        while (!in_stack.isEmpty()) {       // While input stack has elements
            out_stack.push(in_stack.pop()); // Move element to output stack
        }
    }

    public void push(int x) {               // Add element to the queue
        in_stack.push(x);                   // Push element into input stack
    }

    public int pop() {                      // Remove front element
        if (out_stack.isEmpty()) {          // If output stack is empty
            transfer();                     // Move elements from input to output
        }
        return out_stack.pop();             // Remove and return front element
    }

    public int peek() {                     // Get front element
        if (out_stack.isEmpty()) {          // If output stack is empty
            transfer();                     // Move elements from input to output
        }
        return out_stack.peek();            // Return front element
    }

    public boolean empty() {                // Check if queue is empty
        return in_stack.isEmpty() && out_stack.isEmpty(); // True if both stacks are empty
    }
}
