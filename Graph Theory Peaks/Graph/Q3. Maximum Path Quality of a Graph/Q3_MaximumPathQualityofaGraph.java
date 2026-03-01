class Solution {
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        
        int n = values.length;  // number of nodes
        
        // Create adjacency list
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; ++i) 
            adj[i] = new LinkedList<>();
        
        // Build undirected graph
        for (int[] e : edges) {
            int i = e[0], j = e[1], t = e[2];  // nodes and time
            adj[i].add(new int[]{j, t});       // i -> j
            adj[j].add(new int[]{i, t});       // j -> i
        }
        
        int[] res = new int[1];  // store max result (array used for reference)
        int[] seen = new int[n]; // track how many times each node visited
        
        seen[0]++;  // start from node 0
        
        // Start DFS from node 0
        dfs(adj, 0, values, maxTime, seen, res, values[0]);
        
        return res[0];  // return maximum path quality
    }

    private void dfs(List<int[]>[] adj, int src, int[] values, int maxTime, int[] seen, int[] res, int sum) {
        
        // If we are at node 0, update maximum answer
        if (0 == src) {
            res[0] = Math.max(res[0], sum);
        }
        
        // If time exceeded, stop exploring
        if (0 > maxTime) return;
        
        // Explore all neighbors
        for (int[] data : adj[src]) {
            
            int dst = data[0];  // neighbor node
            int t = data[1];    // travel time
            
            // Skip if not enough time
            if (0 > maxTime - t) continue;
            
            seen[dst]++;  // mark visit
            
            // Add value only if visiting first time
            dfs(adj, dst, values, maxTime - t, seen, res, 
                sum + (1 == seen[dst] ? values[dst] : 0));
            
            seen[dst]--;  // backtrack (undo visit)
        }
    }
}