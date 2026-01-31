class Solution {                         // Define the Solution class

    public int magicalString(int n) {    // Function to count number of '1's in magical string of length n

        StringBuilder sb = new StringBuilder("122");  
                                           // Initialize magical string with base "122"

        int w = 2;                         // Pointer to read how many times to repeat

        char r = '1';                     // Current character to append ('1' or '2')

        while (sb.length() < n) {          // Keep building string until length reaches n

            String repeat1 = String.valueOf(r)
                                  .repeat(sb.charAt(w) - '0');  
                                           // Repeat current character r times (based on sb[w])

            sb.append(repeat1);            // Append repeated characters to string

            w++;                           // Move read pointer to next position

            if (r == '1') r = '2';         // Switch character from '1' to '2'
            else r = '1';                  // Switch character from '2' to '1'
        }

        int cnt = 0;                      // Counter for number of '1's

        for (int i = 0; i < n; ++i) {      // Loop through first n characters
            if (sb.charAt(i) == '1')       // If current character is '1'
                cnt++;                     // Increase count
        }

        return cnt;                       // Return total count of '1's
    }
}
