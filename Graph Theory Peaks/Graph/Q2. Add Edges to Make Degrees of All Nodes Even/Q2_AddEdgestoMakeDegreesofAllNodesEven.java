class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        
        // Adjacency list using HashSet (for fast lookup)
        List<Set<Integer>> l = new ArrayList<>();
        
        // Array to store degree of each node (1-based indexing)
        int degree[] = new int[n+1];
        
        // Initialize adjacency list
        for(int i = 0; i < n+1; i++){
            l.add(new HashSet<>());
        }

        // Build graph and count degrees
        for(List<Integer> x : edges){
            l.get(x.get(0)).add(x.get(1));  // add edge u -> v
            l.get(x.get(1)).add(x.get(0));  // add edge v -> u
            degree[x.get(0)]++;             // increase degree of u
            degree[x.get(1)]++;             // increase degree of v
        }

        // Store nodes having odd degree
        List<Integer> odd = new ArrayList<>();
        for(int i = 1; i < n+1; i++){
            if(degree[i] % 2 == 1){  // if degree is odd
                odd.add(i);
            }
        }

        // If all nodes have even degree → already valid
        if(odd.size() == 0) return true;

        // If exactly 2 nodes have odd degree
        if(odd.size() == 2){
            
            Set<Integer> s1 = l.get(odd.get(0));
            Set<Integer> s2 = l.get(odd.get(1));

            // If they are not directly connected → add edge between them
            if(!s1.contains(odd.get(1))){
                return true;
            }
            // If already connected → try connecting both to another node
            else{
                for(int i = 1; i < n+1; i++){
                    if(!s1.contains(i) && !s2.contains(i)){
                        return true;
                    }
                }
            }
        }

        // If exactly 4 nodes have odd degree
        if(odd.size() == 4){
            
            Set<Integer> s1 = l.get(odd.get(0));
            Set<Integer> s2 = l.get(odd.get(1));
            Set<Integer> s3 = l.get(odd.get(2));
            Set<Integer> s4 = l.get(odd.get(3));

            // Case 1: pair (1,2) and (3,4)
            if(!s1.contains(odd.get(1)) && !s3.contains(odd.get(3))){
                return true;
            }
            // Case 2: pair (1,3) and (2,4)
            else if(!s1.contains(odd.get(2)) && !s2.contains(odd.get(3))){
                return true;
            }
            // Case 3: pair (1,4) and (2,3)
            else if(!s1.contains(odd.get(3)) && !s2.contains(odd.get(2))){
                return true;
            }
        }

        // If none of the above conditions satisfied → not possible
        return false;
    }
}