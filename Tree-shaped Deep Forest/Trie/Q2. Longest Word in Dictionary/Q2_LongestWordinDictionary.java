class Solution {
    public String longestWord(String[] words) {
        
        String ans = "";  // Stores the final answer (longest valid word)

        // Create a HashSet for fast lookup (O(1) search)
        Set<String> wordset = new HashSet();

        // Add all words into the set
        for (String word : words) {
            wordset.add(word);
        }

        // Iterate through each word
        for (String word : words) {

            // Check if:
            // 1. Current word is longer than the current answer OR
            // 2. Same length but lexicographically smaller than current answer
            if (word.length() > ans.length() ||
               (word.length() == ans.length() && word.compareTo(ans) < 0)) {

                boolean good = true;  // Flag to check if all prefixes exist

                // Check all prefixes of the word
                // Example: for "apple" → "a", "ap", "app", "appl"
                for (int k = 1; k < word.length(); ++k) {

                    // If any prefix is missing, mark as invalid
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;  // No need to check further
                    }
                }

                // If all prefixes exist, update answer
                if (good) {
                    ans = word;
                }
            }    
        }

        return ans;  // Return the longest valid word
    }
}