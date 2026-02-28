class Solution(object):
    def friendRequests(self, n, restrictions, requests):
        """
        :type n: int                      # Total number of people
        :type restrictions: List[List[int]]  # Pairs that cannot be in same friend group
        :type requests: List[List[int]]      # Friend requests to process
        :rtype: List[bool]                   # Result for each request (True/False)
        """
        
        # Create UnionFind object to manage groups
        union_find = self.UnionFind(n, restrictions)
        
        result = []  # Store answers for each request
        
        # Process each friend request
        for u, v in requests:
            
            # Try to union u and v (make them friends)
            if union_find.union(u, v):
                result.append(True)   # Request accepted
            else:
                result.append(False)  # Request rejected
                
        return result   # Return final results
    

    class UnionFind(object):
        
        def __init__(self, n, restricts):
            
            # Initially each person is their own parent (separate groups)
            self.root = [i for i in range(n)]
            
            # Rank array for union by rank optimization
            self.rank = [1 for i in range(n)]
            
            # Dictionary to store restriction relationships
            self.restrictions = {}
            
            # Build restriction graph
            for u, v in restricts:
                
                # Initialize set if not present
                if u not in self.restrictions:
                    self.restrictions[u] = set()
                if v not in self.restrictions:
                    self.restrictions[v] = set()
                
                # Add mutual restriction
                self.restrictions[u].add(v)
                self.restrictions[v].add(u)
        

        def find(self, x):
            # Path compression: make parent directly root
            if self.root[x] != x:
                self.root[x] = self.find(self.root[x])
            
            return self.root[x]   # Return ultimate parent
        

        def union(self, x, y):
            
            # Find roots of both nodes
            root_x = self.find(x)
            root_y = self.find(y)
            
            # If already in same group, no need to merge
            if root_x == root_y:
                return True
            
            # Check if merging violates restriction
            # If root_x is restricted with root_y → reject
            if ((root_x in self.restrictions and root_y in self.restrictions[root_x]) or
                (root_y in self.restrictions and root_x in self.restrictions[root_y])):
                return False   # Cannot merge
            
            # Perform union by rank
            if self.rank[root_x] < self.rank[root_y]:
                
                # Attach smaller rank tree under larger rank tree
                root = root_y
                child = root_x
                self.root[root_x] = root_y
                    
            elif self.rank[root_y] < self.rank[root_x]:
                
                root = root_x
                child = root_y
                self.root[root_y] = root_x
                    
            else:
                # If ranks equal, choose one and increase rank
                root = root_y
                child = root_x
                self.root[root_x] = root_y
                self.rank[root_y] += 1
                
            # Merge restriction sets after union
            # Child's restrictions now belong to new root
            if child in self.restrictions:
                
                for rest in self.restrictions[child]:
                    
                    # Initialize root restriction set if missing
                    if root not in self.restrictions:
                        self.restrictions[root] = set()
                    
                    # Add restriction of child to root
                    # Use find() to get updated root of restricted node
                    self.restrictions[root].add(self.find(rest))
                
                # Remove old child restriction entry
                del self.restrictions[child]
            
            return True   # Union successful