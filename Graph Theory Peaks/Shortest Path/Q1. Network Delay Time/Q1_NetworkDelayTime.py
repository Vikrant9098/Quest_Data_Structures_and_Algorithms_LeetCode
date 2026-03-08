import collections
import heapq


class Solution:
    def networkDelayTime(self, times, N, K):

        q = [(0, K)]
        # Min heap storing (time, node)

        t = {}
        # Stores shortest time to reach each node

        adj = collections.defaultdict(list)
        # Adjacency list storing neighbors and travel time

        # Build the graph
        for u, v, w in times:
            adj[u].append((v, w))

        while q:
            time, node = heapq.heappop(q)
            # Get node with smallest time from heap

            if node not in t:
                # Process node only once

                t[node] = time
                # Record shortest time to reach this node

                for v, w in adj[node]:
                    # Check all neighbors

                    heapq.heappush(q, (time + w, v))
                    # Push new time for neighbor into heap

        # If all nodes reached return maximum time
        # Otherwise return -1
        return max(t.values()) if len(t) == N else -1