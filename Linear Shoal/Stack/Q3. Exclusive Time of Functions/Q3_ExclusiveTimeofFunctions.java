import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<Q3_ExclusiveTimeofFunctions> stack = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));
        
        for (String content : logs) {
            // Extract the log details from the content(string)
            Q3_ExclusiveTimeofFunctions event = new Q3_ExclusiveTimeofFunctions(content);
            if (event.getIsStart()) {
                // Push the event details to the stack
                stack.push(event);
            } else {
                // Pop the log details from the stack
                Q3_ExclusiveTimeofFunctions top = stack.pop();
                // Add the execution time of the current function to the actual result
                result.set(top.getId(), result.get(top.getId()) + (event.getTime() - top.getTime() + 1));
                // If the stack is not empty, subtract the current child //
// function execution time
                // from the parent function
                if (!stack.isEmpty()) {
                    result.set(stack.peek().getId(),
                            result.get(stack.peek().getId()) - (event.getTime() - top.getTime() + 1));
                }
            }
        }
        
        // Convert List<Integer> to int[]
        int[] resultArray = new int[n];
        for (int i = 0; i < n; i++) {
            resultArray[i] = result.get(i);
        }
        
        return resultArray;
    }
}

public class Q3_ExclusiveTimeofFunctions {
    private int id;
    private boolean isStart;
    private int time;

    public Q3_ExclusiveTimeofFunctions(String content) {
        String[] strs = content.split(":");
        this.id = Integer.parseInt(strs[0]);
        this.isStart = strs[1].equals("start");
        this.time = Integer.parseInt(strs[2]);
    }

    public int getId() {
        return this.id;
    }

    public boolean getIsStart() {
        return this.isStart;
    }

    public int getTime() {
        return this.time;
    }
}