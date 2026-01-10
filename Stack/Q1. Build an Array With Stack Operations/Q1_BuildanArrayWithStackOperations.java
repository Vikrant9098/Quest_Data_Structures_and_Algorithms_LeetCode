// Solution class
class Solution {

    // Method to build operations array
    public static List<String> buildArray(int[] target, int n) {

        // List to store operations
        List<String> result = new ArrayList<>();

        // Current number to be processed
        int current = 1; // starting from 1

        // Loop through each element in target
        for (int i = 0; i < target.length; i++) {

            // If current number is smaller than target value
            while (current < target[i]) {

                // Push the current number
                result.add("Push");

                // Pop it immediately
                result.add("Pop");

                // Move to next number
                current++;
            }

            // When current equals target value, push it
            result.add("Push");

            // Move to next number
            current++;
        }

        // Return the list of operations
        return result;
    }
}
