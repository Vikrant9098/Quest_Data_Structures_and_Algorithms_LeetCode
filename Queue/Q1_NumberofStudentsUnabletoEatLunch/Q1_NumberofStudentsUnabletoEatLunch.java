class Solution {                     // Define the Solution class
    public int countStudents(int[] students, int[] sandwiches) { // Method to count students who can't eat
        
        int[] counts = new int[2];    // Array to count students who like type 0 and type 1
        
        for (int student : students)  // Loop through each student
            counts[student]++;        // Increase count of the student's preference
        
        int remaining = sandwiches.length; // Total number of sandwiches
        
        for (int sandwich : sandwiches) {  // Loop through each sandwich
            if (counts[sandwich] == 0) break; // Stop if no student wants this sandwich
            if (remaining-- == 0) break;     // Reduce remaining count and stop if none left
            counts[sandwich]--;              // One student takes the sandwich
        }
        
        return remaining;             // Return number of students who couldn't eat
    }
}
