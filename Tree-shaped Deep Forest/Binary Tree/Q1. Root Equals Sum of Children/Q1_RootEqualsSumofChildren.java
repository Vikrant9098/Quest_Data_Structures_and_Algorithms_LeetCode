/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;          // value stored in the node
 *     TreeNode left;    // reference to left child
 *     TreeNode right;   // reference to right child
 *     TreeNode() {}     
 *     TreeNode(int val) { this.val = val; }  // constructor to set node value
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;      // assign value
 *         this.left = left;    // assign left child
 *         this.right = right;  // assign right child
 *     }
 * }
 */

class Solution {
    public boolean checkTree(TreeNode root) {

        // check if root value equals sum of left and right child values
        return root.val == root.left.val + root.right.val;
    }
}