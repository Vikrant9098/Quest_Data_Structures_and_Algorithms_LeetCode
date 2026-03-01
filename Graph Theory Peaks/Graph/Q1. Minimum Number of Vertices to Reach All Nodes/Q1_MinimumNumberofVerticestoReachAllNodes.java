class Solution {
    
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        
        // Create an array to store indegree (number of incoming edges) for each node
        int[] indegree = new int[n];
        
        // Traverse each edge in the graph
        for (List<Integer> edge : edges) {
            
            // edge.get(0) = source node
            // edge.get(1) = destination node
            
            // Increase indegree of destination node
            indegree[edge.get(1)]++;
        }

        // List to store nodes with indegree 0
        List<Integer> ans = new ArrayList<>();
        
        // Check every node from 0 to n-1
        for (int i = 0; i < n; i++) {
            
            // If a node has no incoming edges,
            // it means no other node points to it
            if (indegree[i] == 0) {
                
                // So we must include it in the answer
                ans.add(i);
            }
        }

        // Return the list of nodes with indegree 0
        return ans;
    }
}s