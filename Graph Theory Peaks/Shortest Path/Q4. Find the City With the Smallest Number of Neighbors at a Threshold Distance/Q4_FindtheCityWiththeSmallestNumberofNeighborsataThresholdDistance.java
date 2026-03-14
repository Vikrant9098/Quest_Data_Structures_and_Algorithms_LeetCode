class Solution {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        // convert graph into adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // add edges (undirected graph)
        for (int[] edge : edges) {
            int node1 = edge[0], node2 = edge[1], distance = edge[2];
            graph.get(node1).add(new int[]{node2, distance}); // node1 -> node2
            graph.get(node2).add(new int[]{node1, distance}); // node2 -> node1
        }

        int minimum_number = n; // track minimum reachable neighbors
        int res = -1; // store resulting city

        // run shortest path from each city
        for (int source = 0; source < n; source++) {

            // count neighbors reachable within threshold
            int neighbors = get_number_of_neighbors_in_distance(graph, source, n, distanceThreshold);

            // if smaller or equal neighbors found, update result
            // (<= ensures larger index city is chosen in tie)
            if (neighbors <= minimum_number) {
                minimum_number = neighbors;
                res = source;
            }
        }

        return res; // city with smallest reachable neighbors
    }


    private int get_number_of_neighbors_in_distance(List<List<int[]>> graph, int source, int n, int distanceThreshold) {

        // min heap for Dijkstra (distance, node)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        minHeap.add(new int[]{0, source}); // start from source with distance 0

        Set<Integer> visited = new HashSet<>(); // track visited nodes

        while (!minHeap.isEmpty()) {

            int[] top = minHeap.poll();
            int distance_to_this_node = top[0], cur_node = top[1];

            // process node only once
            if (!visited.contains(cur_node)) {

                visited.add(cur_node); // mark visited

                // check all neighbors
                for (int[] neighbor : graph.get(cur_node)) {

                    // distance from source to neighbor
                    int distance_from_source = distance_to_this_node + neighbor[1];

                    // only consider nodes within allowed distance
                    if (distance_from_source <= distanceThreshold) {
                        minHeap.add(new int[]{distance_from_source, neighbor[0]});
                    }
                }
            }
        }

        // subtract 1 because source itself is included
        return visited.size() - 1;
    }
}