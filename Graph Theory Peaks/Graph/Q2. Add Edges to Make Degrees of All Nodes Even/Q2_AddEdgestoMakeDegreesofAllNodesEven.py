class Solution(object):
    def isPossible(self, n, edges):
        
        # Create adjacency list (graph) using sets
        G = [set() for i in range(n)]
        
        # Build undirected graph (convert to 0-based index)
        for i, j in edges:
            G[i-1].add(j-1)  # add edge i -> j
            G[j-1].add(i-1)  # add edge j -> i
        
        # Find nodes with odd degree
        odd = [i for i in range(n) if len(G[i]) % 2]

        # Helper function:
        # Returns True if there is NO edge between a and b
        def f(a, b):
            return a not in G[b]

        # Case 1: If exactly 2 nodes have odd degree
        if len(odd) == 2:
            a, b = odd
            
            # Try connecting both odd nodes to some third node
            # such that new edges do not already exist
            return any(f(a, i) and f(b, i) for i in range(n))

        # Case 2: If exactly 4 nodes have odd degree
        if len(odd) == 4:
            a, b, c, d = odd
            
            # Try all possible pairings of 4 nodes
            return  f(a, b) and f(c, d) or \
                    f(a, c) and f(b, d) or \
                    f(a, d) and f(c, b)

        # Case 3: If no odd nodes → already valid
        return len(odd) == 0