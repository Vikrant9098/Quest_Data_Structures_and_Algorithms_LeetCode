from enum import Enum
import collections

# Enum to represent edge colors
class Color(Enum):
    kInit = 0   # Initial state (no previous edge)
    kRed = 1    # Red edge
    kBlue = 2   # Blue edge


class Solution(object):
    def shortestAlternatingPaths(self, n, redEdges, blueEdges):
        """
        :type n: int
        :type redEdges: List[List[int]]
        :type blueEdges: List[List[int]]
        :rtype: List[int]
        """

        ans = [-1] * n  
        # Result array: shortest alternating distance from node 0
        # Initialized with -1 (means not visited yet)

        graph = [[] for _ in range(n)]  
        # Adjacency list: graph[u] stores (neighbor, edgeColor)

        q = collections.deque([(0, Color.kInit)])  
        # BFS queue storing (node, previousEdgeColor)
        # Start from node 0 with no previous color

        # Build graph with red edges
        for u, v in redEdges:
            graph[u].append((v, Color.kRed))

        # Build graph with blue edges
        for u, v in blueEdges:
            graph[u].append((v, Color.kBlue))

        step = 0  
        # BFS level = distance from node 0

        while q:  
            # Standard BFS traversal

            for _ in range(len(q)):  
                # Process all nodes at the current BFS level

                u, prevColor = q.popleft()  
                # Current node and previous edge color

                if ans[u] == -1:  
                    ans[u] = step  
                    # First time visiting node → shortest distance found

                # Explore neighbors of current node
                for i, (v, edgeColor) in enumerate(graph[u]):

                    # Skip if:
                    # 1. Edge already used (marked by -1)
                    # 2. Edge color is same as previous color (no alternation)
                    if v == -1 or edgeColor == prevColor:
                        continue

                    q.append((v, edgeColor))  
                    # Add neighbor with current edge color as previous color

                    graph[u][i] = (-1, edgeColor)  
                    # Mark this edge as used to prevent revisiting

            step += 1  
            # Increase distance for next BFS level

        return ans  
        # Return shortest alternating paths from node 0