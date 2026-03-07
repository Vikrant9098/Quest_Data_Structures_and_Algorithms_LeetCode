from Queue import Queue          # Import Queue for BFS traversal (Python 2)
from sys import maxsize          # Import a very large value to initialize minimum answer

class Solution(object):
    def minScore(self, n, roads):
        """
        :type n: int
        :type roads: List[List[int]]
        :rtype: int
        """

        ans = maxsize            # Store the minimum road distance found

        # Create adjacency list for graph (cities are 1 to n)
        gr = [[] for _ in range(n + 1)]

        # Build the graph from the roads list
        for edge in roads:
            gr[edge[0]].append((edge[1], edge[2]))   # Add road u -> (v, distance)
            gr[edge[1]].append((edge[0], edge[2]))   # Add road v -> (u, distance) since graph is undirected

        vis = [0] * (n + 1)      # Visited array to track visited cities

        q = Queue()              # Queue for BFS traversal
        q.put(1)                 # Start BFS from city 1
        vis[1] = 1               # Mark city 1 as visited

        # Perform BFS to explore all cities reachable from city 1
        while not q.empty():
            node = q.get()       # Get current city from queue

            # Check all neighboring cities
            for v, dis in gr[node]:

                ans = min(ans, dis)   # Update minimum distance seen so far

                # If neighbor city has not been visited
                if vis[v] == 0:
                    vis[v] = 1        # Mark it as visited
                    q.put(v)          # Add it to queue to continue BFS

        return ans               # Return the minimum road distance found