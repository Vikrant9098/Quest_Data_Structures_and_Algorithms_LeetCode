public class Q3_FindAllNumbersDisappearedinanArray {
    
}
// Solution class
class Solution {

    // Method to find missing numbers in the array
    public List<Integer> findDisappearedNumbers(int[] nums) {

        // Set to store unique numbers from nums
        Set<Integer> set = new HashSet<>();

        // Add each number from nums into the set
        for (int val : nums) {

            // Store value in set
            set.add(val);
        }

        // List to store missing numbers
        ArrayList<Integer> list = new ArrayList<>();

        // Loop from 1 to n (expected numbers)
        for (int i = 1; i <= nums.length; i++) {

            // If number i is not present in set
            if (!set.contains(i)) {

                // Add missing number to list
                list.add(i);
            }
        }

        // Return the list of missing numbers
        return list;
    }
}
