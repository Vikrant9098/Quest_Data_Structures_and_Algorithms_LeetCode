class Solution {
    public int minScore(int n, int[][] roads) {

        // Store the minimum road distance found in the connected component
        int ans = Integer.MAX_VALUE;

        // Adjacency list to store graph -> for each city store (neighbor, distance)
        List<List<Pair<Integer, Integer>>> gr = new ArrayList<>();

        // Create empty list for each city (1 to n)
        for(int i = 0; i < n+1; i++) {
            gr.add(new ArrayList<Pair<Integer, Integer>>());
        }

        // Build the graph from roads array
        for(int[] edge : roads) { 

            // Add edge from u -> v with distance
            gr.get(edge[0]).add(new Pair<>(edge[1], edge[2])); // u -> {v, distance}

            // Add edge from v -> u because graph is undirected
            gr.get(edge[1]).add(new Pair<>(edge[0], edge[2])); // v -> {u, distance}
        }

        // Visited array to track visited cities
        int[] vis = new int[n+1];

        // Initially mark all cities as not visited
        Arrays.fill(vis, 0);

        // Queue for BFS traversal
        Queue<Integer> q = new LinkedList<>();

        // Start BFS from city 1
        q.add(1);

        // Mark city 1 as visited
        vis[1] = 1;

        // BFS traversal
        while(!q.isEmpty()) {

            // Get current city from queue
            int node = q.poll();

            // Traverse all neighbors of current city
            for(Pair<Integer, Integer> pair : gr.get(node)) {

                // Neighbor city
                int v = pair.getKey();

                // Distance of road
                int dis = pair.getValue();

                // Update minimum distance seen so far
                ans = Math.min(ans, dis);

                // If neighbor city not visited yet
                if(vis[v]==0) {

                    // Mark it visited
                    vis[v] = 1;

                    // Add it to queue for further BFS
                    q.add(v);
                }
            }
        }

        // Return the minimum road distance found
        return ans;
    }
}