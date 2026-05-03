class Solution {
    public int maximumPrimeDifference(int[] nums) {
        
        // Initialize two pointers:
        // i -> starting index (left)
        // j -> ending index (right)
        for (int i = 0, j = nums.length - 1; i <= j; ) {
            
            // Check if current left element is prime
            boolean check1 = prime(nums[i]);
            
            // Check if current right element is prime
            boolean check2 = prime(nums[j]);
            
            // If both ends are prime, return the distance between them
            if (check1 && check2) {
                return j - i;
            }
            
            // If left is not prime but right is prime, move left pointer forward
            else if (!check1 && check2) {
                i++;
            }
            
            // If left is prime but right is not prime, move right pointer backward
            else if (check1 && !check2) {
                j--;
            }
            
            // If neither are prime, move both pointers inward
            else {
                i++;
                j--;
            }
        }
        
        // If no valid prime pair is found, return 0
        return 0;
    }

    private boolean prime(int num) {
        
        // Numbers less than or equal to 1 are not prime
        if (num <= 1) {
            return false;
        }
        
        // Check divisibility from 2 to sqrt(num)
        // If divisible by any number, it is not prime
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        
        // If no divisors found, number is prime
        return true;
    }
}