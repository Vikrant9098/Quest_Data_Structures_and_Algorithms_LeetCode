from collections import defaultdict, deque

class Solution:
    def numBusesToDestination(self, routes, source, target):

        if source == target:
            return 0  # No bus needed if source and target are same

        stop_to_routes = defaultdict(list)
        # Map stop to list of route indices passing through it

        # Build mapping of stop to routes
        for i, route in enumerate(routes):
            for stop in route:
                stop_to_routes[stop].append(i)

        visited_routes = set()
        # Track buses (routes) already used

        queue = deque()
        # BFS queue storing route indices

        # Start BFS from routes that include the source stop
        for route_idx in stop_to_routes[source]:
            queue.append(route_idx)
            visited_routes.add(route_idx)

        buses_taken = 1
        # First level means first bus taken

        while queue:

            for _ in range(len(queue)):
                route_idx = queue.popleft()
                # Current bus route

                for stop in routes[route_idx]:
                    # Check all stops of this route

                    if stop == target:
                        return buses_taken
                        # Target stop reached

                    # Find other routes connected through this stop
                    for neighbor_route in stop_to_routes[stop]:

                        if neighbor_route not in visited_routes:
                            visited_routes.add(neighbor_route)
                            queue.append(neighbor_route)
                            # Add new bus route to queue

            buses_taken += 1
            # Increase bus count for next BFS level

        return -1
        # Target cannot be reached