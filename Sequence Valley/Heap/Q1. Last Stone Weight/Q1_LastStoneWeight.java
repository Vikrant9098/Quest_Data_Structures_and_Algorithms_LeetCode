import java.util.PriorityQueue;

class Solution {                                      // Define the Solution class
    public int lastStoneWeight(int[] stones) {        // Method to find last stone weight
        
        //setting decreasing order of the heap or max heap
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a); // Max heap
        
        for (int each : stones)                        // Loop through each stone
            heap.add(each);                            // Add stone to the heap

        while (heap.size() > 1) {                      // Continue while more than one stone
            int top1 = heap.poll();                    // Remove the heaviest stone
            int top2 = heap.poll();                    // Remove the second heaviest stone
            int diff = Math.abs(top1 - top2);          // Find the weight difference

            if (diff != 0) {                           // If stones are not equal
                heap.add(diff);                        // Add the remaining stone back
            }
        }

        if (heap.size() != 0) {                        // If a stone is left
            return heap.poll();                        // Return its weight
        } else {                                       // If no stones are left
            return 0;                                  // Return 0
        }
    }
}
