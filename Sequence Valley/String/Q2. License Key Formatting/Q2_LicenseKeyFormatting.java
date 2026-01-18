class Solution {
    public String licenseKeyFormatting(String S, int K) {
        
        StringBuilder sb = new StringBuilder(); // Use StringBuilder to build the result

        // Traverse the string from end to start
        for (int i = S.length() - 1, count = 0 ; i >= 0 ; --i) {
            
            char c = S.charAt(i); // Get the current character
            if (c == '-') continue; // Skip existing dashes

            // If K characters are already added, append a dash
            if (count == K) {
                sb.append('-');
                count = 0; // Reset the counter
            }

            sb.append(Character.toUpperCase(c)); // Append uppercase character
            ++count; // Increment the count of characters added
        }

        return sb.reverse().toString(); // Reverse the built string and return
    }
}
