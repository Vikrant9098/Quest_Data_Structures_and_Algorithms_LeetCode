class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        
        int[][] g = new int[n][n];                 // Adjacency matrix to store graph with color info
        buildGraph(g, n, red_edges, blue_edges);   // Build graph with encoded edge colors
        
        Queue<int[]> q = new LinkedList<>();       // Queue for BFS traversal
        
        q.offer(new int[]{0, 1});                  // Start from node 0 assuming previous edge was RED
        q.offer(new int[]{0, -1});                 // Start from node 0 assuming previous edge was BLUE
        
        int len = 0;                               // Distance (number of edges from node 0)
        
        int[] res = new int[n];                    // Result array for shortest distance
        Arrays.fill(res, Integer.MAX_VALUE);       // Initialize all distances as infinity
        res[0] = 0;                                // Distance to itself is 0
        
        Set<String> visited = new HashSet<>();     // Track visited states (node + previous color)
        
        while (!q.isEmpty()) {                     // Standard BFS loop
            int size = q.size();                   // Number of nodes at current level
            len++;                                 // Increase distance level
            
            for (int i = 0; i < size; i++) {       // Process all nodes at current BFS level
                int[] cur = q.poll();              // Get current state
                int node = cur[0];                 // Current node
                int color = cur[1];                // Previous edge color
                
                int oppoColor = -color;            // Required next edge color (alternate color)
                
                for (int j = 1; j < n; j++) {      // Check all possible neighbors
                    if (g[node][j] == oppoColor || // Valid if edge has opposite color
                       g[node][j] == 0) {          // Or if both colors exist
                    
                        if (!visited.add(j + "" + oppoColor)) continue; 
                        // Skip if this (node + color state) was already visited
                        
                        q.offer(new int[]{j, oppoColor}); 
                        // Add neighbor with new edge color
                    
                        res[j] = Math.min(res[j], len); 
                        // Update shortest distance
                    }
                }
            }
        }
        
        for (int i = 1; i < n; i++) {               // Convert unreachable nodes
            if (res[i] == Integer.MAX_VALUE) {     // If still infinity
                res[i] = -1;                       // Mark as unreachable
            }
        }
        
        return res;                                // Return shortest alternating distances
    }
    
    private void buildGraph(int[][] g, int n, int[][] red_edges, int[][] blue_edges) {
        
        for (int i = 0; i < n; i++) {               // Initialize graph matrix
            Arrays.fill(g[i], -n);                  // Default value meaning no edge
        }
        
        for (int[] e : red_edges) {                 // Process all red edges
            int from = e[0];                        // Source node
            int to = e[1];                          // Destination node
            g[from][to] = 1;                        // Mark red edge as 1
        }
        
        for (int[] e : blue_edges) {                // Process all blue edges
            int from = e[0];                        // Source node
            int to = e[1];                          // Destination node
            
            if (g[from][to] == 1) {                 // If red edge already exists
                g[from][to] = 0;                    // Mark as both colors available
            } else {
                g[from][to] = -1;                   // Otherwise mark as blue edge
            }
        }
    }
}