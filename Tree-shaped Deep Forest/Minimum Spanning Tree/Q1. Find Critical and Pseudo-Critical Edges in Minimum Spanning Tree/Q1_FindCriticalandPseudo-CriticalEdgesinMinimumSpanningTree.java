class UnionFind {
    private int[] parent;  // Array to store parent of each node

    public UnionFind(int n) {
        parent = new int[n];  // Initialize parent array of size n
        
        // Initially, each node is its own parent (separate components)
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    public int findParent(int p) {
        // If p is its own parent, return p
        // Else recursively find root and apply path compression
        return parent[p] == p ? p : (parent[p] = findParent(parent[p]));
    }

    public void union(int u, int v) {
        // Find root parents of u and v
        int pu = findParent(u), pv = findParent(v);
        
        // Connect root of u to root of v (merge sets)
        parent[pu] = pv;
    }
}

class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        
        // List to store critical edges (must be in MST)
        List<Integer> critical = new ArrayList<>();
        
        // List to store pseudo-critical edges (can be in MST)
        List<Integer> pseudoCritical = new ArrayList<>();
        
        // Add index to each edge to track original position
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];  // Get current edge
            
            // Increase size of edge array by 1 to store index
            edge = Arrays.copyOf(edge, edge.length + 1);
            
            // Store original index at position 3
            edge[3] = i;
            
            // Update edges array
            edges[i] = edge;
        }
        
        // Sort edges based on weight (index 2)
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        // Find MST weight without blocking or forcing any edge
        int mstwt = findMST(n, edges, -1, -1);

        // Iterate through each edge
        for (int i = 0; i < edges.length; i++) {
            
            // If removing this edge increases MST weight → critical edge
            if (mstwt < findMST(n, edges, i, -1))
                critical.add(edges[i][3]);  // Add original index
            
            // If forcing this edge keeps MST weight same → pseudo-critical
            else if (mstwt == findMST(n, edges, -1, i))
                pseudoCritical.add(edges[i][3]);  // Add original index
        }

        // Prepare final result list
        List<List<Integer>> result = new ArrayList<>();
        
        result.add(critical);        // First list → critical edges
        result.add(pseudoCritical);  // Second list → pseudo-critical edges
        
        return result;  // Return result
    }

    private int findMST(int n, int[][] edges, int block, int e) {
        
        // Initialize Union-Find for n nodes
        UnionFind uf = new UnionFind(n);
        
        int weight = 0;  // Total weight of MST

        // If we want to force include edge 'e'
        if (e != -1) {
            weight += edges[e][2];  // Add its weight
            
            // Union its two vertices
            uf.union(edges[e][0], edges[e][1]);
        }

        // Iterate through all edges
        for (int i = 0; i < edges.length; i++) {
            
            // Skip the blocked edge
            if (i == block)
                continue;

            // If both nodes have same parent → cycle → skip
            if (uf.findParent(edges[i][0]) == uf.findParent(edges[i][1]))
                continue;

            // Otherwise include edge in MST
            uf.union(edges[i][0], edges[i][1]);
            
            // Add weight of this edge
            weight += edges[i][2];
        }

        // Check if all nodes are connected (valid MST)
        for (int i = 0; i < n; i++) {
            
            // If any node has different root → graph not fully connected
            if (uf.findParent(i) != uf.findParent(0))
                return Integer.MAX_VALUE;  // Return large value (invalid MST)
        }

        return weight;  // Return total MST weight
    }
}