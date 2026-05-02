class Solution {
    public int findGCD(int[] nums) {

        // Sort the array to easily get minimum and maximum elements
        Arrays.sort(nums);

        // 'a' stores the maximum element
        int a = nums[nums.length - 1];

        // 'b' stores the minimum element
        int b = nums[0];

        // Apply Euclidean Algorithm to find GCD
        while (b > 0) {

            // Store current value of b
            int temp = b;

            // Update b to remainder of a divided by b
            b = a % b;

            // Update a to previous b
            a = temp;
        }

        // 'a' now contains the GCD of min and max element
        return a;
    }
}