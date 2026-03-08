class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        
        if (source == target) return 0; 
        // If source and target are same → no bus needed

        int n = routes.length; 
        
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        // Map: busStop -> list of routes (buses) that pass through that stop

        // Build the mapping of stop → routes
        for (int i = 0; i < n; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.computeIfAbsent(stop, x -> new ArrayList<>()).add(i);
                // Add route index to the stop
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        // BFS queue storing route numbers (buses)

        boolean[] visitedRoutes = new boolean[n];
        // Track visited buses

        Set<Integer> visitedStops = new HashSet<>();
        // Track visited stops (not heavily used here but good practice)

        // Start BFS from all buses available at the source stop
        for (int route : stopToRoutes.getOrDefault(source, new ArrayList<>())) {
            queue.offer(route);          // add route to BFS
            visitedRoutes[route] = true; // mark route as visited
        }

        int busesTaken = 1; 
        // First level = taking first bus

        while (!queue.isEmpty()) {
            int size = queue.size(); // number of routes at current BFS level

            for (int i = 0; i < size; i++) {
                int routeIdx = queue.poll(); // current bus route

                // Check all stops of this route
                for (int stop : routes[routeIdx]) {

                    if (stop == target) return busesTaken;
                    // If this route reaches target stop → return buses used

                    // Find other routes connected through this stop
                    for (int neighborRoute : stopToRoutes.getOrDefault(stop, new ArrayList<>())) {

                        // If that route is not visited yet
                        if (!visitedRoutes[neighborRoute]) {
                            visitedRoutes[neighborRoute] = true; 
                            queue.offer(neighborRoute); // take that bus
                        }
                    }
                }
            }

            busesTaken++; 
            // Moving to next level = taking one more bus
        }

        return -1; 
        // Target stop cannot be reached
    }
}