class Solution {
    public int rob(int[] nums) {

        // Get the number of houses
        int n = nums.length;

        // If there is only one house, return its value
        if (n == 1) {
            return nums[0];
        }

        // Create a DP array where dp[i] stores the maximum money
        // that can be robbed up to house i
        int[] dp = new int[n];

        // Base case: Only first house available
        dp[0] = nums[0];

        // Base case: Choose max between first and second house
        dp[1] = Math.max(nums[0], nums[1]);

        // Iterate through remaining houses
        for (int i = 2; i < n; i++) {

            // Either skip current house (dp[i-1])
            // OR rob current house + value from i-2 (nums[i] + dp[i-2])
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        // Return maximum money that can be robbed till last house
        return dp[n - 1];        
    }
}