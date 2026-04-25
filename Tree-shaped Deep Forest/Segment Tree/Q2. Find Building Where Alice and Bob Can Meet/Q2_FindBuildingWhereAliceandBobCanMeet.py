class Solution(object):
    def leftmostBuildingQueries(self, heights, queries):
        """
        :type heights: List[int]
        :type queries: List[List[int]]
        :rtype: List[int]
        """

        mono_stack = []  # monotonic decreasing stack (height, index)
        result = [-1 for _ in range(len(queries))]  # default answers = -1

        # store pending queries grouped by index b
        new_queries = [[] for _ in range(len(heights))]

        # preprocess queries
        for i in range(len(queries)):
            a = queries[i][0]
            b = queries[i][1]

            if a > b:
                a, b = b, a  # ensure a <= b

            # direct answer case
            if heights[b] > heights[a] or a == b:
                result[i] = b
            else:
                # store for later processing at index b
                new_queries[b].append((heights[a], i))

        # traverse from right to left
        for i in range(len(heights) - 1, -1, -1):

            mono_stack_size = len(mono_stack)  # current stack size

            # process queries waiting at index i
            for a, b in new_queries[i]:
                position = self.search(a, mono_stack)  # find taller building

                if position < mono_stack_size and position >= 0:
                    result[b] = mono_stack[position][1]  # store index

            # maintain decreasing stack (remove smaller heights)
            while mono_stack and mono_stack[-1][0] <= heights[i]:
                mono_stack.pop()

            # add current building
            mono_stack.append((heights[i], i))

        return result  # return final answers

    def search(self, height, mono_stack):
        # binary search for building with height > given height
        left = 0
        right = len(mono_stack) - 1
        ans = -1

        while left <= right:
            mid = (left + right) // 2

            if mono_stack[mid][0] > height:
                ans = max(ans, mid)  # update answer
                left = mid + 1  # move right
            else:
                right = mid - 1  # move left

        return ans  # return position