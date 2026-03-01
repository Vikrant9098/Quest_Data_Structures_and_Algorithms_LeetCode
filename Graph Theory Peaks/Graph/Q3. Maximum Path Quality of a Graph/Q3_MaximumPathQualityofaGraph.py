from collections import defaultdict


class Solution:
    def maximalPathQuality(self, values, E, maxTime):
        
        # Build graph using adjacency list
        G = defaultdict(list)
        for x, y, w in E:
            G[x].append((y, w))  # edge x -> y with time w
            G[y].append((x, w))  # edge y -> x (undirected graph)
            
        # DFS function
        # node → current node
        # visited → set of visited nodes
        # gain → total value collected so far
        # cost → remaining time
        def dfs(node, visited, gain, cost):
            
            # If we return to node 0, update maximum answer
            if node == 0:
                self.ans = max(self.ans, gain)
            
            # Try all neighbors
            for neib, w in G[node]:
                
                # Only move if we have enough remaining time
                if w <= cost:
                    
                    # Add neighbor's value only if visiting first time
                    dfs(
                        neib,
                        visited | set([neib]),  # add neighbor to visited
                        gain + (neib not in visited) * values[neib],  # add value if new
                        cost - w  # reduce remaining time
                    )

        # Initialize answer
        self.ans = 0
        
        # Start DFS from node 0
        dfs(0, set([0]), values[0], maxTime)
        
        # Return maximum path quality
        return self.ans