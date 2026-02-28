class Solution {

    // Stores the parent of each node (Disjoint Set / Union-Find)
    HashMap<Integer, Integer> parent;

    // Stores the size of each connected component (only valid for root nodes)
    HashMap<Integer, Integer> size;

    // Initialize the parent and size maps
    public void init(){
        parent = new HashMap<>();
        size = new HashMap<>();
    }

    // Create a new group (set) for number n
    public void newGroup(int n){
        // If already exists, do nothing
        if(parent.containsKey(n)) return;

        // Initially, a node is its own parent (self root)
        parent.put(n, n);

        // Initial size of each component is 1
        size.put(n, 1);
    }

    // Find the ultimate parent (root) of x
    public int find(int x){
        // If x is its own parent, it is the root
        if(parent.get(x)==x){
            return x;
        }

        // Recursively find the root
        return find(parent.get(x));
    }

    // Union two nodes a and b (merge their components)
    public void union(int a, int b){

        // Find root of a
        int parentA = find(a);

        // Find root of b
        int parentB = find(b);

        // If both have same parent, already connected
        if(parentA==parentB) return;

        // Union by size (attach smaller tree under bigger tree)
        if(size.get(parentA)>=size.get(parentB)){

            // Make parentB's root point to parentA
            parent.put(parentB, parentA);

            // Increase size of parentA component
            size.put(parentA, size.get(parentA)+size.get(parentB));
        }else{

            // Make parentA's root point to parentB
            parent.put(parentA, parentB);

            // Increase size of parentB component
            size.put(parentB, size.get(parentA)+size.get(parentB));
        }
    }

    // Get all factors (excluding 1) of number a
    public ArrayList<Integer> getFactors(int a){

        // List to store factors
        ArrayList<Integer> list = new ArrayList<>();

        // Iterate from 2 to sqrt(a)
        for(int i=2; i<=Math.sqrt(a); i++){

            // If i divides a, then both i and a/i are factors
            if(a%i==0){
                list.add(i);
                list.add(a/i);
            }
        }

        // Add the number itself (important for prime numbers)
        list.add(a);

        return list;
    }

    public int largestComponentSize(int[] nums) {

        int n = nums.length;

        // Map: factor -> list of numbers having that factor
        // This acts like a graph connection via common factors
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        // Initialize DSU structure
        init();

        // Step 1: Build groups and factor mapping
        for(int num : nums){

            // Make each number its own set initially
            newGroup(num);

            // Get factors of num
            ArrayList<Integer> factors = new ArrayList<>(getFactors(num));

            // Map each factor to the numbers having that factor
            for(int f : factors){

                // If factor not present in map, create list
                map.putIfAbsent(f, new ArrayList<>());

                // Add current number to factor's list
                ArrayList<Integer> list = map.get(f);
                list.add(num);
            }
        }

        // Step 2: Union numbers that share the same factor
        for(int key : map.keySet()){

            // Get all numbers that share this factor
            ArrayList<Integer> list = map.get(key);

            // Union consecutive numbers in the list
            for(int i=0; i<list.size()-1; i++){

                // Connect list[i] and list[i+1]
                union(list.get(i), list.get(i+1));
            }
        }

        // Step 3: Find maximum component size
        int max = Integer.MIN_VALUE;

        for(int num : nums){

            // Find root of this number
            int par = find(num);

            // Update maximum size among components
            max = Math.max(size.get(par), max);
        }

        // Return largest connected component size
        return max;
    }
}