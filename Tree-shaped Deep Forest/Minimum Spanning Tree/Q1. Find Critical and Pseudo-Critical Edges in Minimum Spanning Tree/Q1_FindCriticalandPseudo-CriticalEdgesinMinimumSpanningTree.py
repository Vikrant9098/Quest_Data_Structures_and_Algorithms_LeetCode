class UnionFind(object):
    def __init__(self, n):
        # Initially, each node is its own parent (separate components)
        self.parent = list(range(n))

    def find_parent(self, p):
        # If p is the root, return it
        if self.parent[p] == p:
            return p
        
        # Path compression: make the parent of p point directly to root
        self.parent[p] = self.find_parent(self.parent[p])
        return self.parent[p]

    def union(self, u, v):
        # Find root parents of u and v
        pu = self.find_parent(u)
        pv = self.find_parent(v)
        
        # Merge the two components
        self.parent[pu] = pv


class Solution(object):
    def findCriticalAndPseudoCriticalEdges(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: List[List[int]]
        """

        # Lists to store result
        critical = []
        pseudo_critical = []

        # Add index to each edge to track original position
        for i in range(len(edges)):
            edges[i].append(i)

        # Sort edges based on weight (3rd element)
        edges.sort(key=lambda x: x[2])

        # Find MST weight without any restriction
        mst_wt = self.find_mst(n, edges, -1, -1)

        # Check each edge
        for i in range(len(edges)):

            # Case 1: If removing this edge increases MST weight → Critical
            if mst_wt < self.find_mst(n, edges, i, -1):
                critical.append(edges[i][3])

            # Case 2: If forcing this edge keeps MST same → Pseudo-Critical
            elif mst_wt == self.find_mst(n, edges, -1, i):
                pseudo_critical.append(edges[i][3])

        return [critical, pseudo_critical]


    def find_mst(self, n, edges, block, e):
        # Create Union-Find structure
        uf = UnionFind(n)
        weight = 0  # Total MST weight

        # If we force include edge 'e'
        if e != -1:
            weight += edges[e][2]  # Add its weight
            uf.union(edges[e][0], edges[e][1])  # Connect its nodes

        # Try to build MST using Kruskal’s logic
        for i in range(len(edges)):

            # Skip blocked edge
            if i == block:
                continue

            u, v, w = edges[i][0], edges[i][1], edges[i][2]

            # If adding this edge forms a cycle → skip
            if uf.find_parent(u) == uf.find_parent(v):
                continue

            # Otherwise include edge in MST
            uf.union(u, v)
            weight += w

        # Check if all nodes are connected (valid MST)
        root = uf.find_parent(0)
        for i in range(n):
            if uf.find_parent(i) != root:
                return float('inf')  # Not connected → invalid MST

        return weight  # Return MST total weight