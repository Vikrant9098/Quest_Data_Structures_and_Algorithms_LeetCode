public class Q1_PeakIndexinaMountainArray {                 // Define the class named Solution

    public int peakIndexInMountainArray(int[] arr) {  // Method to find peak index

        int low = 0;                      // Start pointer at beginning of array
        int high = arr.length - 1;        // End pointer at last index

        while (low < high) {              // Loop until both pointers meet

            int mid = low + (high - low) / 2; // Find middle index safely

            if (arr[mid] < arr[mid + 1]) {    // If next element is bigger
                low = mid + 1;                // Peak is on the right side
            } else if (arr[mid] > arr[mid + 1]) { // If current is bigger than next
                high = mid;                   // Peak is on the left or at mid
            }
        }

        return low;                        // low points to the peak index
    }
}
