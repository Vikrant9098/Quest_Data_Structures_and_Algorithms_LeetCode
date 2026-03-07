class Solution(object):
    def allPathsSourceTarget(self, graph):
        """
        :type graph: List[List[int]]
        :rtype: List[List[int]]
        """

        result = []
        
        # Stack will store tuples of (current_node, path_so_far)
        stack = [(0, [0])]
        
        # Target node is the last node
        target = len(graph) - 1
        
        # Continue until stack becomes empty
        while stack:
            cur, route = stack.pop()
            
            # If we reach the target node, store the path
            if cur == target:
                result.append(route)
            else:
                # Explore all neighbors of the current node
                for node in graph[cur]:
                    # Add new path including this neighbor
                    stack.append((node, route + [node]))
        
        return result