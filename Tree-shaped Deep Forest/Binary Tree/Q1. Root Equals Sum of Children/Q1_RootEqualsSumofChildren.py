# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val      # value of the node
#         self.left = left    # reference to left child
#         self.right = right  # reference to right child

class Solution(object):
    def checkTree(self, root):
        """
        :type root: Optional[TreeNode]   # root of the binary tree
        :rtype: bool                     # return True or False
        """

        # check if root value equals sum of its left and right child values
        return root.val == root.left.val + root.right.val