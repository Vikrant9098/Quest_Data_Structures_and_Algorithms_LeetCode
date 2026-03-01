import collections


class Solution(object):
    def validPath(self, n, edges, source, destination):
        """
        :type n: int
        :type edges: List[List[int]]
        :type source: int
        :type destination: int
        :rtype: bool
        """
        
        # Create adjacency list using dictionary
        graph = collections.defaultdict(list)
        
        # Build undirected graph
        for u, v in edges:
            graph[u].append(v)  # add edge u -> v
            graph[v].append(u)  # add edge v -> u
        
        # DFS function to check if path exists
        def dfs(node, visited):
            
            # If we reach destination, path exists
            if node == destination:
                return True
            
            # Mark current node as visited
            visited.add(node)
            
            # Explore all neighbors
            for neighbor in graph[node]:
                
                # Visit only unvisited nodes
                if neighbor not in visited:
                    
                    # If any recursive call reaches destination → return True
                    if dfs(neighbor, visited):
                        return True
            
            # If no path found
            return False
        
        # Set to track visited nodes (avoid infinite loops)
        visited = set()
        
        # Start DFS from source
        return dfs(source, visited)