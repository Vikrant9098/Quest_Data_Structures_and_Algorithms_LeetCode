import java.util.*;                          // Import utility classes

class Solution {                             // Define Solution class

   public int reversePairs(int[] nums) {     // Main function to count reverse pairs
       return mergeSort(nums, 0, nums.length - 1); // Call merge sort on full array
   }
   
   private static void merge(int[] nums, int low, int mid, int high) {
       ArrayList<Integer> temp = new ArrayList<>(); // Temporary list for merging
       int left = low;                       // Pointer for left subarray
       int right = mid + 1;                  // Pointer for right subarray
       
       while (left <= mid && right <= high) { // Merge while both halves have elements
           if (nums[left] <= nums[right]) {  // If left element is smaller
               temp.add(nums[left++]);       // Add left element and move pointer
           } else {                           // If right element is smaller
               temp.add(nums[right++]);      // Add right element and move pointer
           }
       }
       while (left <= mid) temp.add(nums[left++]);   // Add remaining left elements
       while (right <= high) temp.add(nums[right++]); // Add remaining right elements
       
       for (int i = low; i <= high; i++) {   // Copy sorted elements back to array
           nums[i] = temp.get(i - low);
       }
   }
   
   private static int CountPairs(int[] nums, int low, int mid, int high) {
       int cnt = 0;                          // Store number of reverse pairs
       int right = mid + 1;                  // Pointer for right subarray
       
       for (int i = low; i <= mid; i++) {    // Traverse left subarray
           while (right <= high && (long) nums[i] > 2L * nums[right]) {
               right++;                      // Move right pointer while condition holds
           }
           cnt += (right - (mid + 1));       // Count valid reverse pairs
       }
       return cnt;                           // Return count
   }
   
   private static int mergeSort(int[] nums, int low, int high) {
       if (low >= high) return 0;            // Base case: one or no element
       
       int mid = (low + high) / 2;           // Find middle index
       
       int cnt = mergeSort(nums, low, mid);  // Count in left half
       cnt += mergeSort(nums, mid + 1, high); // Count in right half
       cnt += CountPairs(nums, low, mid, high); // Count cross pairs
       
       merge(nums, low, mid, high);          // Merge both halves
       
       return cnt;                           // Return total count
   }
}
