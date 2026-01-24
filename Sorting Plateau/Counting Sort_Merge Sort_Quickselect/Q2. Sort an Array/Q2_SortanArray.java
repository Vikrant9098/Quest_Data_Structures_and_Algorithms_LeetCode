class Solution {

    // Main function called by LeetCode
    public int[] sortArray(int[] nums) {

        // Call merge sort on the full array
        mergeSort(nums, 0, nums.length - 1);

        // Return the sorted array
        return nums;
    }

    // Function to merge two sorted subarrays
    // arr[l..m] and arr[m+1..r]
    public static void mergeFun(int[] arr, int l, int m, int r) {

        // Number of elements in left subarray
        int n1 = m + 1 - l;

        // Number of elements in right subarray
        int n2 = r - m;

        // Temporary array to store left half
        int[] left = new int[n1];

        // Copy left half elements
        for (int i = 0; i < n1; i++) {
            left[i] = arr[l + i];
        }

        // Temporary array to store right half
        int[] right = new int[n2];

        // Copy right half elements
        for (int i = 0; i < n2; i++) {
            right[i] = arr[m + 1 + i];
        }

        // i -> index for left array
        // j -> index for right array
        // k -> index for original array
        int i = 0, j = 0, k = l;

        // Merge both arrays back into arr
        while (i < n1 || j < n2) {

            // If right array is exhausted
            // OR left element is smaller than right element
            if (j == n2 || (i < n1 && left[i] < right[j])) {

                // Place left element into original array
                arr[k++] = left[i++];

            } else {

                // Place right element into original array
                arr[k++] = right[j++];
            }
        }
    }

    // Recursive merge sort function
    public static void mergeSort(int[] arr, int low, int high) {

        // Continue only if there are at least two elements
        if (low < high) {

            // Find middle index safely
            int middle = (high - low) / 2 + low;

            // Sort left half
            mergeSort(arr, low, middle);

            // Sort right half
            mergeSort(arr, middle + 1, high);

            // Merge the sorted halves
            mergeFun(arr, low, middle, high);
        }
    }
}
