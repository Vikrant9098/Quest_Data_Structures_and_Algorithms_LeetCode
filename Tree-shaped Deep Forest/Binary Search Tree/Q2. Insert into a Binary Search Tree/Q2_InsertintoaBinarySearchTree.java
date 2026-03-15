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
    public TreeNode insertIntoBST(TreeNode root, int val) {

        // if tree/subtree is empty, create and return new node
        if (root == null) return new TreeNode(val);

        // if value is smaller, insert into left subtree
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } 
        // otherwise insert into right subtree
        else {
            root.right = insertIntoBST(root.right, val);
        }

        // return current root to maintain tree structure
        return root;
    }
}