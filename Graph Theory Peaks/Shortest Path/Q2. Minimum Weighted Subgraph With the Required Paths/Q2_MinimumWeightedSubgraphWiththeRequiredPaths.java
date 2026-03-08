class Solution {

    ArrayList<int[]>[] nextGraph, preGraph; 
    // nextGraph stores normal edges (from -> to)
    // preGraph stores reverse edges (to -> from)

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {

        buildGraph(n, edges); 
        // Build both forward and reverse graphs

        long[] src1To = new long[n], src2To = new long[n], toDest = new long[n];
        // Arrays to store shortest distances

        Arrays.fill(src1To, -1); 
        // Initialize src1 distances as -1 (not visited)

        Arrays.fill(src2To, -1); 
        // Initialize src2 distances as -1 (not visited)

        Arrays.fill(toDest, -1); 
        // Initialize dest distances as -1 (not visited)

        shortestPath(src1, src1To, nextGraph); 
        // Find shortest path from src1 to all nodes

        shortestPath(src2, src2To, nextGraph); 
        // Find shortest path from src2 to all nodes

        shortestPath(dest, toDest, preGraph); 
        // Find shortest path to dest using reverse graph

        long res = -1; 
        // Variable to store minimum result

        for (int i = 0; i < n; i++) { 
            // Check every node as possible meeting point

            long d1 = src1To[i], d2 = src2To[i], d3 = toDest[i]; 
            // d1 = src1 -> i
            // d2 = src2 -> i
            // d3 = i -> dest

            if (d1 >= 0 && d2 >= 0 && d3 >= 0) { 
                // Check if all paths exist

                long d = d1 + d2 + d3; 
                // Total distance through node i

                if (res == -1 || d < res) { 
                    // If result not set or smaller distance found

                    res = d; 
                    // Update minimum result
                }
            }
        }

        return res; 
        // Return minimum total weight
    }

    private void buildGraph(int n, int[][] edges) {

        nextGraph = new ArrayList[n]; 
        // Create forward graph array

        preGraph = new ArrayList[n]; 
        // Create reverse graph array

        for (int i = 0; i < n; i++) { 
            // Initialize lists for each node

            nextGraph[i] = new ArrayList<int[]>(); 
            // List for outgoing edges

            preGraph[i] = new ArrayList<int[]>(); 
            // List for incoming edges
        }

        for (int[] edge : edges) { 
            // Traverse all edges

            int from = edge[0], to = edge[1], weight = edge[2]; 
            // Extract edge details

            nextGraph[from].add(new int[] {to, weight}); 
            // Add edge in forward graph

            preGraph[to].add(new int[] {from, weight}); 
            // Add reverse edge in reverse graph
        }
    }

    private void shortestPath(int src, long[] srcTo, ArrayList<int[]>[] graph) {

        PriorityQueue<long[]> heap =
            new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1])); 
        // Min heap based on distance

        heap.offer(new long[] {src, 0}); 
        // Start from source node with distance 0

        while (!heap.isEmpty()) { 
            // Run until heap becomes empty

            long[] node = heap.poll(); 
            // Get node with smallest distance

            int to = (int) node[0]; 
            // Current node

            long dist = node[1]; 
            // Distance to current node

            if (srcTo[to] != -1 && srcTo[to] <= dist) continue; 
            // Skip if a shorter path already found

            srcTo[to] = dist; 
            // Update shortest distance

            for (int[] next : graph[to]) { 
                // Visit all neighbors

                heap.offer(new long[] {next[0], dist + next[1]}); 
                // Push neighbor with new distance
            }
        }
    }
}