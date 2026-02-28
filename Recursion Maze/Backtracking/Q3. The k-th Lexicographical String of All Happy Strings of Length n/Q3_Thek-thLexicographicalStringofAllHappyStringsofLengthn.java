class Solution {
    
    int n2;   // Stores original length n (used for calculating remaining combinations)
    
    public String getHappyString(int n, int k) {
        n2 = n;   // Save original n
        return dfs(new StringBuilder(), n, k);  // Start DFS with empty prefix
    }
    
    public String dfs(StringBuilder prefix, int n, int k){
        
        // Base case: if no more characters to add
        if (n == 0)
            return prefix.toString();   // Return completed happy string
        
        // Try characters from 'a' to 'c'
        for (char c = 'a'; c <= 'c'; c++) {
            
            // Skip if same as previous character (to maintain happy string condition)
            if (prefix.length() > 0 && c == prefix.charAt(prefix.length() - 1))
                continue;
            
            // Count how many valid strings can be formed
            // if we choose this character now
            int cnt = (int) Math.pow(2, n2 - prefix.length() - 1);
            
            // If the k-th string lies in this block
            if (cnt >= k)
                return dfs(prefix.append(c), n - 1, k);  // Choose this char and go deeper
            
            else
                k -= cnt;  // Skip this block and reduce k
        }
        
        return "";  // If no valid string found, return empty
    }
}