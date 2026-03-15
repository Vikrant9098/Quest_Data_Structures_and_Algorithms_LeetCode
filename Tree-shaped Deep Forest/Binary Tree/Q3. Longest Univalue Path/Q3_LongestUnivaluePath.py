# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution(object):
    def longestUnivaluePath(self, root):
        """
        :type root: Optional[TreeNode]
        :rtype: int
        """
        self.max = 0  # stores maximum nodes in same-value path

        def dfs(node):
            if not node:
                return 0  # base case

            left = dfs(node.left)    # longest path from left subtree
            right = dfs(node.right)  # longest path from right subtree

            left_arrow = 0
            right_arrow = 0

            # extend path to left if value matches
            if node.left and node.left.val == node.val:
                left_arrow = left

            # extend path to right if value matches
            if node.right and node.right.val == node.val:
                right_arrow = right

            # update global max path passing through current node
            self.max = max(self.max, left_arrow + right_arrow + 1)

            # return longest single direction path upward
            return max(left_arrow, right_arrow) + 1

        dfs(root)  # start DFS traversal

        # convert node count to edge count
        return self.max - 1 if self.max > 0 else 0