from heapq import heappop, heappush
from collections import defaultdict

class Solution(object):
    def minimumWeight(self, n, edges, src1, src2, dest):
        """
        :type n: int
        :type edges: List[List[int]]
        :type src1: int
        :type src2: int
        :type dest: int
        :rtype: int
        """

        G = defaultdict(list)
        # Graph: node -> list of (neighbor, weight)

        for v, w, c in edges:
            G[v].append((w, c))
            # Build forward graph

        def Dijkstra(G, dist, src):
            heap = [(0, src)]
            # Min heap storing (cost, node)

            while heap:
                c, v = heappop(heap)
                # Get node with smallest cost

                if dist[v] != float('inf'):
                    continue
                    # Skip if already visited

                dist[v] = c
                # Record shortest distance

                for w, cw in G[v]:
                    # Check neighbors

                    if dist[w] == float('inf'):
                        heappush(heap, (c + cw, w))
                        # Push neighbor with updated cost

        dist_src1 = defaultdict(lambda: float('inf'))
        # Distance from src1 to all nodes

        Dijkstra(G, dist_src1, src1)
        # Run Dijkstra from src1

        dist_src2 = defaultdict(lambda: float('inf'))
        # Distance from src2 to all nodes

        Dijkstra(G, dist_src2, src2)
        # Run Dijkstra from src2

        G = defaultdict(list)
        # Create reverse graph

        for v, w, c in edges:
            G[w].append((v, c))
            # Reverse edges

        dist_dest = defaultdict(lambda: float('inf'))
        # Distance from nodes to destination

        Dijkstra(G, dist_dest, dest)
        # Run Dijkstra from dest on reverse graph

        res = min([dist_src1[i] + dist_src2[i] + dist_dest[i] for i in range(n)])
        # Try every node as meeting point

        return res if res != float('inf') else -1
        # If path exists return cost else return -1