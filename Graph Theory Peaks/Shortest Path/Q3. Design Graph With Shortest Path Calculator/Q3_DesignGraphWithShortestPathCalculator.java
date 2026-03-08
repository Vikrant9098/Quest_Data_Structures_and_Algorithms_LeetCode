import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Graph {

    private List<List<int[]>> adjacencyList;
    // Adjacency list to store graph
    // Each node stores list of (neighbor, weight)

    public Graph(int n, int[][] edges) {

        adjacencyList = new ArrayList<>(n);
        // Create list to hold neighbors of each node

        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
            // Initialize empty list for every node
        }

        for (int[] edge : edges) {
            addEdge(edge);
            // Add each edge to the graph
        }
    }

    public void addEdge(int[] edge) {

        int[] edgeArray = {edge[1], edge[2]};
        // edgeArray stores (destination, weight)

        adjacencyList.get(edge[0]).add(edgeArray);
        // Add edge from source to destination
    }

    public int shortestPath(int node1, int node2) {

        return dijkstra(node1, node2);
        // Call Dijkstra algorithm to find shortest path
    }

    private int dijkstra(int start, int end) {

        int n = adjacencyList.size();
        // Number of nodes

        int[] distances = new int[n];
        // distances[i] stores shortest distance from start to node i

        Arrays.fill(distances, Integer.MAX_VALUE);
        // Initialize all distances as infinity

        distances[start] = 0;
        // Distance to start node is 0

        // Priority queue to get node with smallest distance
        PriorityQueue<int[]> priorityQueue =
                new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        priorityQueue.add(new int[]{0, start});
        // Add start node with cost 0

        while (!priorityQueue.isEmpty()) {

            int[] current = priorityQueue.poll();
            // Get node with smallest cost

            int currentNode = current[1], currentCost = current[0];
            // Extract node and its cost

            if (currentCost > distances[currentNode])
                continue;
            // Skip if a shorter path already exists

            if(currentNode == end)
                return currentCost;
            // If target reached return cost

            for (int[] edge : adjacencyList.get(currentNode)) {
                // Check all neighbors of current node

                int neighbor = edge[0], edgeLength = edge[1];
                // Neighbor node and edge weight

                int newRouteCost = edgeLength + distances[currentNode];
                // Total cost to reach neighbor

                if (distances[neighbor] > newRouteCost) {
                    // If shorter path found

                    distances[neighbor] = newRouteCost;
                    // Update shortest distance

                    priorityQueue.add(new int[]{newRouteCost, neighbor});
                    // Add neighbor to queue for further processing
                }
            }
        }

        return ((distances[end] == Integer.MAX_VALUE) ? -1 : distances[end]);
        // Return -1 if destination not reachable
    }
}