class Solution {

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {

        List<Pair<Integer, Integer>> monoStack = new ArrayList<>(); // monotonic stack (height, index)
        int[] result = new int[queries.length]; // final answers
        Arrays.fill(result, -1); // default = -1 (no answer)

        List<List<Pair<Integer, Integer>>> newQueries = new ArrayList<>(heights.length); 
        // store pending queries grouped by index b

        for (int i = 0; i < heights.length; i++) {
            newQueries.add(new ArrayList<>()); // init list for each index
        }

        // preprocess queries
        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            if (a > b) { // ensure a <= b
                int temp = a;
                a = b;
                b = temp;
            }

            // direct answer case
            if (heights[b] > heights[a] || a == b) {
                result[i] = b; // b itself is valid
            } else {
                // store query for later processing
                newQueries.get(b).add(new Pair<>(heights[a], i));
            }
        }

        // traverse from right to left
        for (int i = heights.length - 1; i >= 0; i--) {

            int monoStackSize = monoStack.size(); // current stack size

            // process all queries waiting at index i
            for (Pair<Integer, Integer> pair : newQueries.get(i)) {

                int position = search(pair.getKey(), monoStack); // find taller building

                if (position < monoStackSize && position >= 0) {
                    result[pair.getValue()] = monoStack.get(position).getValue(); 
                    // store index of valid building
                }
            }

            // maintain decreasing stack (remove smaller heights)
            while (
                !monoStack.isEmpty() &&
                monoStack.get(monoStack.size() - 1).getKey() <= heights[i]
            ) {
                monoStack.remove(monoStack.size() - 1);
            }

            // push current building
            monoStack.add(new Pair<>(heights[i], i));
        }

        return result; // return final answers
    }

    // binary search for first building with height > given height
    private int search(int height, List<Pair<Integer, Integer>> monoStack) {

        int left = 0;
        int right = monoStack.size() - 1;
        int ans = -1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (monoStack.get(mid).getKey() > height) {
                ans = Math.max(ans, mid); // update answer
                left = mid + 1; // move right
            } else {
                right = mid - 1; // move left
            }
        }

        return ans; // return position
    }
}