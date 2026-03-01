class Solution(object):
    def findSmallestSetOfVertices(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: List[int]
        """
        
        # range(n) → creates all nodes from 0 to n-1
        # set(range(n)) → convert all nodes into a set
        
        # (j for i, j in edges) → take only destination nodes from edges
        # set(j for i, j in edges) → set of all nodes that have incoming edges
        
        # Subtract destination nodes from all nodes
        # Remaining nodes are those with indegree = 0
        
        # Convert the result back to list and return
        return list(set(range(n)) - set(j for i, j in edges))