class Graph(object):

    def __init__(self, n, edges):
        self.n = n  # number of nodes

        # create distance matrix and initialize with infinity
        self.distance = [[float('inf')] * n for _ in range(n)]

        # distance from node to itself is 0
        for i in range(n):
            self.distance[i][i] = 0

        # set direct edge distances
        for edge in edges:
            self.distance[edge[0]][edge[1]] = edge[2]

        # Floyd-Warshall algorithm to compute all-pairs shortest paths
        for k in range(n):          # intermediate node
            for i in range(n):      # start node
                for j in range(n):  # end node
                    self.distance[i][j] = min(
                        self.distance[i][j],                     # current shortest
                        self.distance[i][k] + self.distance[k][j]  # path through k
                    )

    def addEdge(self, edge):

        # if existing path is already shorter, ignore this edge
        if self.distance[edge[0]][edge[1]] <= edge[2]:
            return

        # update direct edge weight
        self.distance[edge[0]][edge[1]] = edge[2]

        # update shortest paths using the new edge
        for i in range(self.n):      # start node
            for j in range(self.n):  # end node
                self.distance[i][j] = min(
                    self.distance[i][j],  # current shortest path
                    self.distance[i][edge[0]] + edge[2] + self.distance[edge[1]][j]  # path using new edge
                )

    def shortestPath(self, node1, node2):

        # if no path exists
        if self.distance[node1][node2] == float('inf'):
            return -1

        # return shortest distance
        return self.distance[node1][node2]