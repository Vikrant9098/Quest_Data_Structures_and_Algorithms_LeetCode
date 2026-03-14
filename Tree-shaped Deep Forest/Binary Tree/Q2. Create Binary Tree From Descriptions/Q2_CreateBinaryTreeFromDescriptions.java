class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {

        Set<Integer> childrenSet = new HashSet<>(); // store all nodes that appear as children
        Map<Integer, int[]> childrenHashmap = new HashMap<>(); // parent -> [leftChild, rightChild]

        for (int[] desc : descriptions) {
            int parent = desc[0];          // parent node value
            int child = desc[1];           // child node value
            boolean isLeft = desc[2] == 1; // 1 means left child, 0 means right child

            // create entry for parent if not present
            childrenHashmap.putIfAbsent(parent, new int[]{-1, -1});

            childrenSet.add(child); // record child nodes to help find root later

            if (isLeft) {
                childrenHashmap.get(parent)[0] = child; // set left child
            } else {
                childrenHashmap.get(parent)[1] = child; // set right child
            }
        }

        int headNodeVal = 0; // will store root node value

        // find root (node that never appears as a child)
        for (int parent : childrenHashmap.keySet()) {
            if (!childrenSet.contains(parent)) {
                headNodeVal = parent;
                break;
            }
        }

        // build tree starting from root
        return constructTree(headNodeVal, childrenHashmap);
    }

    private TreeNode constructTree(int curNodeVal, Map<Integer, int[]> childrenHashmap) {

        TreeNode newNode = new TreeNode(curNodeVal); // create node

        // check if this node has children
        if (childrenHashmap.containsKey(curNodeVal)) {

            int[] children = childrenHashmap.get(curNodeVal); // get left and right child values

            if (children[0] != -1) {
                // recursively build left subtree
                newNode.left = constructTree(children[0], childrenHashmap);
            }

            if (children[1] != -1) {
                // recursively build right subtree
                newNode.right = constructTree(children[1], childrenHashmap);
            }
        }

        return newNode; // return constructed subtree
    }
}