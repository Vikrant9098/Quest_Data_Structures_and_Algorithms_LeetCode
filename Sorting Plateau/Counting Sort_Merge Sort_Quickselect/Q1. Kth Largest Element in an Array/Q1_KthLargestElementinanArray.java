import java.util.PriorityQueue;

class Solution {
    // Solution class

    public int findKthLargest(int[] nums, int k) {
        // Method to find the k-th largest element

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // Min-heap to store k largest elements

        for (int no : nums) {
            // Loop through each number in the array

            queue.add(no);
            // Add current number to the heap

            if (queue.size() > k) {
                // If heap size exceeds k

                queue.remove();
                // Remove the smallest element
            }
        }

        return queue.poll();
        // Return the k-th largest element
    }
}
