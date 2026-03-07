class Solution(object):
    def countCompleteComponents(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: int
        """
        
        from collections import deque
        
        # Create adjacency list to represent the graph
        adj = [[] for _ in range(n)]
        
        # Build the undirected graph
        for u, v in edges:
            adj[u].append(v)   # add v as neighbor of u
            adj[v].append(u)   # add u as neighbor of v
        
        # Track visited nodes to avoid processing them again
        vis = [False] * n
        
        # Store number of complete components
        ans = 0
        
        # BFS function to find all nodes in the current connected component
        def bfs(node):
            q = deque([node])     # queue for BFS traversal
            vis[node] = True      # mark starting node as visited
            comp = []             # list to store nodes of this component
            
            while q:
                cur = q.popleft()  # get next node from queue
                comp.append(cur)   # add node to component list
                
                # Explore all neighbors of current node
                for neighbor in adj[cur]:
                    if not vis[neighbor]:       # if neighbor not visited
                        vis[neighbor] = True    # mark it visited
                        q.append(neighbor)      # add it to queue
            
            return comp   # return all nodes belonging to this component
        
        # Traverse all nodes to find different connected components
        for i in range(n):
            if not vis[i]:             # if node not yet visited
                
                comp = bfs(i)          # get all nodes in this component
                
                # Check if the component forms a complete graph
                # In a complete graph each node connects to (size - 1) nodes
                if all(len(adj[node]) == len(comp) - 1 for node in comp):
                    ans += 1           # count this as a complete component
        
        return ans