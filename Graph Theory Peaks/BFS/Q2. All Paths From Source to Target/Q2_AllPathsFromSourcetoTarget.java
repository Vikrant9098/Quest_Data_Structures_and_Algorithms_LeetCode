class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        
        // List to store all possible paths
        List<List<Integer>> ans = new LinkedList<>();
        
        // List to store current path while doing DFS
        List<Integer> current = new ArrayList<>();
        
        // Start path from node 0
        current.add(0);
        
        // Call DFS from source (0) to destination (last node)
        dfs(0, current, graph, graph.length - 1, ans);
        
        // Return all collected paths
        return ans; 
    }

    private void dfs(int src, List<Integer> current, int graph[][], int dest, List<List<Integer>> ans){
        
        // If we reached destination node
        if(src == dest){
            
            // Add a copy of current path to answer
            ans.add(new ArrayList<>(current));
            return;
        }
        
        // Traverse all neighbors of current node
        for(int n : graph[src]){
            
            // Add neighbor to current path
            current.add(n);
            
            // Recursively explore further
            dfs(n, current, graph, dest, ans);
            
            // Backtrack: remove last added node
            current.remove(current.size() - 1);
        }
    }
}