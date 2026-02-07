class Solution {
    public String longestPrefix(String s) {

        int[] dp = new int[s.length()];   // dp[i] stores length of longest prefix = suffix till index i

        int len = 0, i = 1;               // len = current matched prefix length, i = current index

        while (i < s.length()) {          // Loop through the string

            if (s.charAt(i) == s.charAt(len)) {   // If characters match
                dp[i] = ++len;             // Increase match length and store it
                i++;                       // Move to next character
            } 
            else {                         // If characters do not match

                if (len > 0) {             // If we have previous matches
                    len = dp[len - 1];     // Fall back to shorter prefix
                } 
                else {                     // If no match at all
                    dp[i] = 0;             // No prefix-suffix match here
                    i++;                   // Move forward
                }
            }
        }

        return s.substring(0, dp[dp.length - 1]); // Return longest prefix which is also suffix
    }
}
