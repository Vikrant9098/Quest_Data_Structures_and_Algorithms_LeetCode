# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution(object):
    def insertIntoBST(self, root, val):
        """
        :type root: Optional[TreeNode]
        :type val: int
        :rtype: Optional[TreeNode]
        """

        if root is None:
            return TreeNode(val)  # found insertion position → create new node

        # if value is smaller, go to left subtree
        if root.val > val:
            root.left = self.insertIntoBST(root.left, val)

        # otherwise insert in right subtree
        else:
            root.right = self.insertIntoBST(root.right, val)

        return root  # return current root after insertion