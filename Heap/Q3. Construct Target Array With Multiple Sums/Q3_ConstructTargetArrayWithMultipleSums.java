import java.util.Collections;

public boolean isPossible(int[] target) {          // Check if target array can be built
    if (target.length == 1)                        // If only one element
        return target[0] == 1;                     // It must be 1

    PriorityQueue<Integer> pq =
        new PriorityQueue<>(Collections.reverseOrder()); // Max heap to get largest value
    int sum = 0;                                   // Store sum of all elements

    for (int t : target) {                         // Loop through target array
        pq.add(t);                                 // Add element to heap
        sum += t;                                  // Add element to total sum
    }

    while (pq.peek() != 1) {                       // Continue until largest is 1
        int curr = pq.poll();                      // Remove largest element

        if (sum - curr == 1)                       // If remaining sum is 1
            return true;                           // Target is possible

        int x = curr % (sum - curr);               // Reduce current value
        sum = sum - curr + x;                      // Update total sum

        if (x == 0 || x == curr)                   // If no valid change
            return false;                          // Not possible
        else
            pq.add(x);                             // Add updated value back to heap
    }

    return true;                                   // All elements reduced to 1
}
