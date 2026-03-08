class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        
        boolean[] visited = new boolean[friends.length]; // Track visited friends
        Queue<Integer> queue = new ArrayDeque<>();       // Queue for BFS traversal
        
        queue.offer(id);      // Start BFS from given person
        visited[id] = true;   // Mark starting person as visited
        
        // BFS until we reach the required friendship level
        while (!queue.isEmpty() && level-- > 0) {
            int size = queue.size();  // Number of nodes at current level
            
            while (size-- > 0) {
                int cur = queue.poll(); // Current friend
                
                // Explore all direct friends of current person
                for (int i : friends[cur]) {
                    if (!visited[i]) {
                        queue.offer(i);   // Add friend to queue
                        visited[i] = true; // Mark as visited
                    }
                }
            }
        }

        // If no friends exist at required level
        if (queue.isEmpty() || level > 0) return new ArrayList<>();

        Map<String, Integer> freq = new HashMap<>(); // Store video watch frequency

        // Collect videos watched by friends at target level
        for (int i : queue) {
            for (String video : watchedVideos.get(i)) {
                freq.put(video, freq.getOrDefault(video, 0) + 1); // Count frequency
            }
        }

        // Create result list from video names
        List<String> res = new ArrayList<>(freq.keySet());

        // Sort by frequency first, then lexicographically
        Collections.sort(res, (e1, e2) -> 
            freq.get(e1) == freq.get(e2) 
            ? e1.compareTo(e2)              // If freq same → sort by name
            : Integer.compare(freq.get(e1), freq.get(e2)) // Else sort by freq
        );

        return res; // Return sorted video list
    }
}