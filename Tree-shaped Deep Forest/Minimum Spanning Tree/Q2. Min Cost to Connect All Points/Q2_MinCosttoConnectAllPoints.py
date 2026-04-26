class Solution(object):
    def findCriticalAndPseudoCriticalEdges(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: List[List[int]]
        """

        # Union-Find (Disjoint Set)
        class UnionFind(object):
            def __init__(self, n):
                # Initialize parent array where each node is its own parent
                self.parent = list(range(n))

            def find_parent(self, p):
                # If p is its own parent, return it (root found)
                if self.parent[p] == p:
                    return p
                
                # Otherwise recursively find root and compress path
                self.parent[p] = self.find_parent(self.parent[p])
                
                # Return the root parent
                return self.parent[p]

            def union(self, u, v):
                # Find root parent of u
                pu = self.find_parent(u)
                
                # Find root parent of v
                pv = self.find_parent(v)
                
                # Connect root of u to root of v (merge sets)
                self.parent[pu] = pv

        # Helper function to build MST
        def find_mst(n, edges, block, e):
            # Create a new Union-Find instance
            uf = UnionFind(n)
            
            # Initialize total weight of MST
            weight = 0

            # If we must force include edge 'e'
            if e != -1:
                # Add its weight
                weight += edges[e][2]
                
                # Union its two vertices
                uf.union(edges[e][0], edges[e][1])

            # Iterate through all edges
            for i in range(len(edges)):

                # If this edge is blocked, skip it
                if i == block:
                    continue

                # If both vertices already have same parent → cycle → skip
                if uf.find_parent(edges[i][0]) == uf.find_parent(edges[i][1]):
                    continue

                # Otherwise include this edge in MST
                uf.union(edges[i][0], edges[i][1])

                # Add its weight
                weight += edges[i][2]

            # Check if all nodes are connected (valid MST)
            for i in range(n):
                # If any node is not connected to node 0 → not fully connected
                if uf.find_parent(i) != uf.find_parent(0):
                    return float('inf')  # Return infinity (invalid MST)

            # Return total MST weight
            return weight

        # Main Logic

        # Store indices of critical edges
        critical = []
        
        # Store indices of pseudo-critical edges
        pseudo_critical = []

        # Add index to each edge → [u, v, weight, original_index]
        for i in range(len(edges)):
            edges[i].append(i)

        # Sort edges based on weight (for Kruskal's algorithm)
        edges.sort(key=lambda x: x[2])

        # Find MST weight without any restriction
        mst_wt = find_mst(n, edges, -1, -1)

        # Check each edge
        for i in range(len(edges)):

            # Case 1: If removing this edge increases MST weight → critical edge
            if mst_wt < find_mst(n, edges, i, -1):
                critical.append(edges[i][3])  # Store original index

            # Case 2: If forcing this edge still gives same MST → pseudo-critical
            elif mst_wt == find_mst(n, edges, -1, i):
                pseudo_critical.append(edges[i][3])  # Store original index

        # Return result
        return [critical, pseudo_critical]