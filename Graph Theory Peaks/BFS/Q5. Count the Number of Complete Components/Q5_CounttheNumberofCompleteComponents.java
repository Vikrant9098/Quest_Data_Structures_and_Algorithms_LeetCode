import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {

        // Create adjacency list for graph
        List<List<Integer>> graph = new ArrayList<>();

        // Initialize empty list for each node
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Build the graph (undirected)
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]); // edge u -> v
            graph.get(edge[1]).add(edge[0]); // edge v -> u
        }
        
        // Track visited nodes
        boolean[] visited = new boolean[n];

        // Count of complete connected components
        int completeComponents = 0;
        
        // Traverse all nodes
        for (int i = 0; i < n; i++) {

            // If node not visited, explore its component
            if (!visited[i]) {

                // Store all nodes in this connected component
                Set<Integer> component = new HashSet<>();

                // Perform DFS to collect nodes of this component
                dfs(i, graph, visited, component);

                // Check if this component forms a complete graph
                if (isComplete(component, graph)) {
                    completeComponents++;
                }
            }
        }
        
        // Return number of complete components
        return completeComponents;
    }
    
    private void dfs(int node, List<List<Integer>> graph, boolean[] visited, Set<Integer> component) {

        // Mark current node as visited
        visited[node] = true;

        // Add node to current component
        component.add(node);

        // Traverse all neighbors
        for (int neighbor : graph.get(node)) {

            // If neighbor not visited, continue DFS
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, component);
            }
        }
    }
    
    private boolean isComplete(Set<Integer> component, List<List<Integer>> graph) {

        // Size of current connected component
        int size = component.size();

        // Check every node in the component
        for (int node : component) {

            // In a complete graph, each node must connect to (size - 1) nodes
            if (graph.get(node).size() != size - 1) {
                return false;
            }
        }

        // If all nodes satisfy condition, component is complete
        return true;
    }
}