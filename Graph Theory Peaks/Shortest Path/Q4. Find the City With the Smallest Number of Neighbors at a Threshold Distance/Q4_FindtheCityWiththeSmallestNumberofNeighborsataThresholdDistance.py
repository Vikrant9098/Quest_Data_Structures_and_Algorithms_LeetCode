from heapq import heappop, heappush


class Solution(object):
    def findTheCity(self, n, edges, distanceThreshold):
        """
        :type n: int
        :type edges: List[List[int]]
        :type distanceThreshold: int
        :rtype: int
        """

        # convert graph into adjacency list
        graph = [[] for _ in range(n)]  # graph[i] stores neighbors of node i

        for node1, node2, distance in edges:
            graph[node1].append([node2, distance])  # edge node1 -> node2
            graph[node2].append([node1, distance])  # edge node2 -> node1 (undirected)

        # function to count neighbors reachable within distanceThreshold
        def get_number_of_neighbors_in_distance(source):

            # min heap for Dijkstra (distance, node)
            queue = [(0, source)]  # distance to source itself is 0
            visited = set()

            while queue:
                distance_to_this_node, cur_node = heappop(queue)

                # process node only once
                if cur_node not in visited:
                    visited.add(cur_node)

                    # check all neighbors
                    for neighbor, distance in graph[cur_node]:

                        # new distance from source
                        distance_from_source = distance_to_this_node + distance

                        # only consider nodes within allowed distance
                        if distance_from_source <= distanceThreshold:
                            heappush(queue, (distance_from_source, neighbor))

            # subtract 1 because source itself is included
            return len(visited) - 1

        minimum_number = n  # track minimum reachable neighbors
        res = None          # store resulting city

        # run Dijkstra from every city
        for source in range(n):

            neighbors = get_number_of_neighbors_in_distance(source)

            # update result if smaller neighbors found
            # <= ensures larger index city is chosen in tie
            if neighbors <= minimum_number:
                minimum_number = neighbors
                res = source

        return res