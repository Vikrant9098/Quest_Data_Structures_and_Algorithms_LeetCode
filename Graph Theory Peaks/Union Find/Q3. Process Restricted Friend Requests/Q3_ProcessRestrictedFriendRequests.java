class Solution {
    
    // Classic Union-Find method to find the root (ultimate parent)
    private int findParent(int[] parent, int index) {
        
        // If a person is their own parent, return it (root found)
        if(parent[index] == index) return index;
        
        // Otherwise recursively find the root parent
        return findParent(parent, parent[index]);
    }
   
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        
        // Number of restrictions and number of requests
        int resLength = restrictions.length, reqLength = requests.length;
        
        // Result array to store True/False for each request
        boolean[] result = new boolean[reqLength];
        
        // Parent array for Union-Find
        int[] parent = new int[n];
        
        // Initially every person is their own parent (separate group)
        for(int i = 0; i < n; i++) 
            parent[i] = i;
        
        // Process each friend request
        for(int i = 0; i < reqLength; i++) {
            
            // Find root parent of both people in the request
            int firstParent = findParent(parent, requests[i][0]);
            int secondParent = findParent(parent, requests[i][1]);
            
            // If already in same group, friendship is allowed
            if(firstParent == secondParent) {
                result[i] = true;
                continue; // move to next request
            }
            
            // Check if merging violates any restriction
            boolean flag = true;
            
            // Check all restrictions
            for(int j = 0; j < resLength; j++) {
                
                // Find root parents of restricted pair
                int firstRestriction = findParent(parent, restrictions[j][0]);
                int secondRestriction = findParent(parent, restrictions[j][1]);
                
                // If merging would connect restricted groups → not allowed
                if((firstRestriction == firstParent && secondRestriction == secondParent) || 
                   (secondRestriction == firstParent && firstRestriction == secondParent)) {
                    flag = false;
                    break; // stop checking further
                }
            }
            
            // If no restriction violated
            if(flag) {
                
                result[i] = true; // friendship allowed
                
                // Merge the two groups (Union step)
                parent[firstParent] = secondParent; 
            }
        }
        
        // Return final result array
        return result;
    }
}