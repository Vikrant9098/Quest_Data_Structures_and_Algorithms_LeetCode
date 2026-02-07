class Solution {

    // Arrays to store prefix hashes and powers for two hash functions
    public long[] hsh, hsh2, pw, pw2;

    // Mod value to avoid overflow
    public int mod = (int) 1e9 + 7;

    // Main function to calculate sum of scores
    public long sumScores(String s) {

        // Length of the string and hash bases
        int n = s.length(), base = 131, base2 = 137;

        // Initialize hash and power arrays
        hsh = new long[n + 1];
        pw = new long[n + 1];
        hsh2 = new long[n + 1];
        pw2 = new long[n + 1];

        // Base case: power of 0 is 1
        pw[0] = 1;
        pw2[0] = 1;

        // Build prefix hashes and power arrays
        for (int j = 1; j <= n; j++) {
            // First hash calculation
            hsh[j] = (hsh[j - 1] * base + s.charAt(j - 1)) % mod;

            // Store power of base
            pw[j] = pw[j - 1] * base % mod;

            // Second hash calculation (for double hashing)
            hsh2[j] = (hsh2[j - 1] * base2 + s.charAt(j - 1)) % mod;

            // Store power of second base
            pw2[j] = pw2[j - 1] * base2 % mod;
        }

        // Variable to store final answer
        long ans = 0;

        // Loop from end of string to start
        for (int i = n; i >= 1; i--) {

            // Skip if first character doesn't match
            if (s.charAt(i - 1) != s.charAt(0)) continue;

            // Binary search range
            int lo = 0, hi = n - i + 1, res = 0;

            // Binary search to find longest matching prefix
            while (lo <= hi) {
                int mid = (lo + hi) >> 1; // Middle length

                // Compare prefix hash with substring hash
                if (getSubstrHash(0, mid) == getSubstrHash(i - 1, i + mid - 1)) {
                    lo = mid + 1; // Try longer match
                    res = mid;    // Store valid length
                } else {
                    hi = mid - 1; // Try shorter match
                }
            }

            // Add longest match length to answer
            ans += res;
        }

        // Return total score
        return ans;
    }

    // Function to get hash of substring [l, r)
    public long getSubstrHash(int l, int r) {

        // First hash value of substring
        long h1 = (hsh[r] - hsh[l] * pw[r - l] % mod + mod) % mod;

        // Second hash value of substring
        long h2 = (hsh2[r] - hsh2[l] * pw2[r - l] % mod + mod) % mod;

        // Combine both hashes into one long value
        return (h1 << 31) | h2;
    }
}
