/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int max = 0; // stores maximum nodes in same-value path

    public int longestUnivaluePath(TreeNode root) {
        maxPath(root); // start DFS traversal
        return max > 0 ? max - 1 : 0; // convert nodes count to edges count
    }

    private int maxPath(TreeNode node) {
        if (node == null) return 0; // base case

        int left = maxPath(node.left);   // longest path from left subtree
        int right = maxPath(node.right); // longest path from right subtree

        int leftArrow = 0, rightArrow = 0; // valid paths with same value

        // extend path from left if value matches
        if (node.left != null && node.left.val == node.val) {
            leftArrow = left;
        }

        // extend path from right if value matches
        if (node.right != null && node.right.val == node.val) {
            rightArrow = right;
        }

        // update global maximum path passing through this node
        max = Math.max(max, leftArrow + rightArrow + 1);

        // return longest single direction path upward
        return Math.max(leftArrow, rightArrow) + 1;
    }
}