import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        // Step 1: Build graph
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        // Map: node -> (neighbor, weight)

        for(int[] time : times) {
            int start = time[0];   // starting node
            int end = time[1];     // destination node
            int weight = time[2];  // time to reach destination

            map.putIfAbsent(start, new HashMap<>()); 
            // create map entry if not present

            map.get(start).put(end, weight); 
            // store neighbor and travel time
        }

        // Step 2: Distance array
        int[] dis = new int[n+1]; 
        // dis[i] stores shortest time to reach node i

        Arrays.fill(dis, Integer.MAX_VALUE); 
        // initialize all distances as infinity

        dis[k] = 0; 
        // starting node distance is 0

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{k,0});
        // queue stores (node, currentTime)

        // Step 3: BFS / relaxation process
        while(!queue.isEmpty()) {

            int[] cur = queue.poll(); 
            // get current node

            int curNode = cur[0];     
            int curWeight = cur[1];  
            // current distance from start

            for(int next : map.getOrDefault(curNode, new HashMap<>()).keySet()) {
                // check all neighbors

                int nextweight = map.get(curNode).get(next); 
                // edge weight to neighbor

                if(curWeight + nextweight < dis[next]) {
                    // if shorter path found

                    dis[next] = curWeight + nextweight; 
                    // update shortest distance

                    queue.add(new int[]{next, curWeight + nextweight}); 
                    // add neighbor to queue with updated distance
                }
            }
        }

        // Step 4: Find maximum distance
        int res = 0;

        for(int i=1; i<=n; i++) {
            if(dis[i] > res) {
                res = Math.max(res, dis[i]); 
                // track maximum delay
            } 
        }

        return res == Integer.MAX_VALUE ? -1 : res;
        // return -1 if any node is unreachable
    }
}