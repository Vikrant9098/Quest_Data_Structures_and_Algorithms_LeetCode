import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int minCostConnectPoints(int[][] points) {

        int n = points.length;                 // Total number of points (nodes)
        int min_cost = 0;                      // Stores final minimum cost (MST total weight)

        boolean[] visited = new boolean[n];    // Tracks whether a node is already included in MST

        // Min-heap (Priority Queue) storing {cost, node}
        // Always picks the edge with minimum cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Cache to store the minimum cost required to connect each node
        // Helps avoid pushing unnecessary larger distances
        Map<Integer, Integer> cache = new HashMap<>();

        pq.offer(new int[]{0, 0});             // Start from node 0 with cost 0

        // Process until all nodes are added to MST
        while (!pq.isEmpty()) {

            int[] edge = pq.poll();            // Get the minimum cost edge
            int cost = edge[0];                // Extract cost
            int u = edge[1];                   // Extract node

            // If node is already included in MST, skip it
            if (visited[u]) {
                continue;
            }

            visited[u] = true;                 // Mark node as visited (included in MST)
            min_cost += cost;                  // Add its cost to total MST cost

            // Try to connect this node (u) to all other nodes (v)
            for (int v = 0; v < n; v++) {

                // Only consider nodes not yet included in MST
                if (!visited[v]) {

                    // Calculate Manhattan distance between points u and v
                    int dist = Math.abs(points[u][0] - points[v][0]) +
                               Math.abs(points[u][1] - points[v][1]);

                    // If this distance is smaller than previously known best distance
                    if (dist < cache.getOrDefault(v, Integer.MAX_VALUE)) {

                        cache.put(v, dist);   // Update best known cost for node v

                        // Add this edge into priority queue
                        pq.offer(new int[]{dist, v});
                    }
                }
            }
        }

        return min_cost;                       // Return total cost of MST
    }
}