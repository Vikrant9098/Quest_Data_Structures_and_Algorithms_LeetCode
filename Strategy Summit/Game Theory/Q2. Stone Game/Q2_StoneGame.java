class Solution {
    public boolean stoneGame(int[] piles) {
        
        // Total number of piles
        int N = piles.length;

        // dp[i+1][j+1] represents the maximum score difference
        // the current player can achieve over the opponent
        // for the subarray piles[i...j]
        // We use (N+2) to safely handle boundary conditions
        int[][] dp = new int[N + 2][N + 2];

        // size represents the length of the current subarray
        for (int size = 1; size <= N; ++size)
            
            // i is the starting index of the subarray
            for (int i = 0; i + size <= N; ++i) {
                
                // j is the ending index of the subarray
                int j = i + size - 1;

                // Determine whose turn it is based on parity
                // If parity == 1 → first player's turn (maximize)
                // Else → second player's turn (minimize)
                // Note: (j + i + N) % 2 is equivalent to (j - i - N) % 2
                int parity = (j + i + N) % 2;

                // First player's turn: try to maximize score difference
                if (parity == 1)
                    dp[i + 1][j + 1] = Math.max(
                        // Pick left pile and add its value
                        piles[i] + dp[i + 2][j + 1],
                        
                        // Pick right pile and add its value
                        piles[j] + dp[i + 1][j]
                    );
                
                // Second player's turn: try to minimize first player's advantage
                else
                    dp[i + 1][j + 1] = Math.min(
                        // Opponent picks left → subtract value
                        -piles[i] + dp[i + 2][j + 1],
                        
                        // Opponent picks right → subtract value
                        -piles[j] + dp[i + 1][j]
                    );
            }

        // If final score difference > 0, first player wins
        return dp[1][N] > 0;
    }
}