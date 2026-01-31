class Solution {                                   // Define the Solution class

    public int[] beautifulArray(int n) {            // Method to create a beautiful array of size n
        int[] ans = new int[n];                      // Create an array to store numbers
        for(int i = 0; i  < n; i++){                 // Loop through the array
            ans[i] = i + 1;                          // Fill array with values from 1 to n
        }
        recursion(ans, 0, n - 1);                    // Apply recursive rearrangement
        return ans;                                  // Return the final beautiful array
    }
    
    public void recursion(int[] arr, int left, int right){ // Recursive function to rearrange array
        if(left >= right)                            // If only one or no element
            return;                                  // Stop recursion
        
        ArrayList<Integer> l = new ArrayList<>();    // List to store alternate elements (group 1)
        ArrayList<Integer> r = new ArrayList<>();    // List to store remaining elements (group 2)
		
        boolean alt = true;                           // Flag to alternate between two groups
        // Elements are picked alternately to avoid arithmetic mean condition
							
        for(int i = left; i <= right; i++){          // Traverse the current subarray
            if(alt)                                  // If flag is true
                l.add(arr[i]);                       // Add element to left group
            else                                     // Otherwise
                r.add(arr[i]);                       // Add element to right group
            alt = !alt;                              // Flip the flag
        }

        for(int i = left; i <= right; i++){          // Merge both groups back into array
            if(!l.isEmpty())                         // If left group still has elements
                arr[i] = l.remove(0);                // Take element from left group
            else                                     // Otherwise
                arr[i] = r.remove(0);                // Take element from right group
        }

        recursion(arr, left, (left + right) / 2);    // Recursively process left half
        recursion(arr, (left + right) / 2 + 1, right); // Recursively process right half
    }
}
