class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        
        // Create adjacency list representation of graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        // Build undirected graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            // Add v to u's list
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            
            // Add u to v's list (because graph is undirected)
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        
        // Set to track visited nodes (to avoid cycles)
        Set<Integer> visited = new HashSet<>();
        
        // Start DFS from source
        return dfs(source, destination, graph, visited);
    }
    
    private boolean dfs(int node, int destination, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        
        // If we reached destination → path exists
        if (node == destination) {
            return true;
        }
        
        // Mark current node as visited
        visited.add(node);
        
        // Traverse all neighbors of current node
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            
            // Visit only unvisited neighbors
            if (!visited.contains(neighbor)) {
                
                // If any path leads to destination → return true
                if (dfs(neighbor, destination, graph, visited)) {
                    return true;
                }
            }
        }
        
        // If no path found
        return false;
    }
}