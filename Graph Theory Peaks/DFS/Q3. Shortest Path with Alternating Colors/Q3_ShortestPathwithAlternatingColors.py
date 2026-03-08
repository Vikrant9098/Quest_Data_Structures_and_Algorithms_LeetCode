class Solution(object):
    def shortestAlternatingPaths(self, n, redEdges, blueEdges):
        """
        :type n: int
        :type redEdges: List[List[int]]
        :type blueEdges: List[List[int]]
        :rtype: List[int]
        """

        if n == 0:
            return []   # Edge case: no nodes

        # Build adjacency list
        adjList = {i: [] for i in range(n)}  
        # Example: {0:[(1,'r')], 1:[], 2:[(1,'b')]}

        # Add red edges to graph
        for start, end in redEdges:
            adjList[start].append((end, 'r'))   # (neighbor, red)

        # Add blue edges to graph
        for start, end in blueEdges:
            adjList[start].append((end, 'b'))   # (neighbor, blue)

        ans = [-1] * n      # Result array storing shortest alternating distance
        ans[0] = 0          # Distance from node 0 to itself is 0

        # Start BFS from neighbors of node 0
        stack = []
        for nextPtr, color in adjList[0]:
            stack.append((nextPtr, color))   # (node, edgeColor)

        # Track visited states (node + incoming color)
        visited = {(0, 'r'), (0, 'b')}  
        # Prevent returning back to node 0

        level = 0   # BFS level = path length

        # BFS traversal
        while stack:
            level += 1              # Move to next level
            nextStack = []          # Store nodes for next BFS layer

            for node, color in stack:

                # Update shortest distance for this node
                if ans[node] == -1:
                    ans[node] = level
                else:
                    ans[node] = min(ans[node], level)

                # Explore neighbors
                for nextPtr, nextColor in adjList[node]:

                    # Only move if edge color alternates
                    # and this state not visited before
                    if color != nextColor and (nextPtr, nextColor) not in visited:
                        nextStack.append((nextPtr, nextColor))
                        visited.add((nextPtr, nextColor))  # Mark visited state

            stack = nextStack   # Move to next BFS layer

        return ans   # Return shortest alternating path distances